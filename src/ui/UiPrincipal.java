package ui;

import controle.Sistema;
import modelo.Atividade;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class UiPrincipal {

    // ============================================
    // Atributos
    // ============================================
    protected UiUsuario uiUsuario;
    protected UiSala uiSala;
    protected UiGrupo uiGrupo;
    protected UiAtividade uiAtividade;
    protected SimpleDateFormat sdf;
    protected Scanner scn = new Scanner(System.in);
    protected Sistema sistema;

    // ============================================
    // Construtor
    // ============================================
    public UiPrincipal() {
        uiUsuario = new UiUsuario();
        uiSala = new UiSala();
        uiGrupo = new UiGrupo();
        uiAtividade = new UiAtividade();
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sistema = Sistema.getInstance();
    }

    // ============================================
    // Tela Inicial
    // ============================================
    public void telaInicial() {
        Sistema sistema = Sistema.getInstance();

        while (true) {
            limparTela();
            System.out.println("=== EscolaSync ===");

            if (sistema.getUsuarioAtual() == null) {
                if (!mostrarTelaLogin()) {
                    return;
                }
            } else {
                if (!mostrarTelaPrincipal()) {
                    continue;
                }
            }
        }
    }

    // ============================================
    // Tela de Sala
    // ============================================
    public void telaSala(int codigoSala) {
        limparTela();
        Sistema sistema = Sistema.getInstance();
        Sala sala = sistema.buscarSala(codigoSala);

        if (sala == null) {
            System.out.println("Sala não encontrada!");
            return;
        }

        Usuario usuarioAtual = sistema.getUsuarioAtual();
        boolean usuarioEstaNaSala = sistema.usuarioEstaNaSala(sala.getId(), usuarioAtual.getUsername());

        if (!usuarioEstaNaSala) {
            System.out.println("Você não está nesta sala!");
            System.out.println("\nO que deseja fazer?");
            System.out.println("[1] Entrar na sala");
            System.out.println("[0] Voltar");

            int opcao = scn.nextInt();
            scn.nextLine();

            if (opcao == 1) {
                if (sistema.entrarSala(sala.getId())) {
                    System.out.println("Entrou na sala com sucesso!");
                    usuarioEstaNaSala = true;
                } else {
                    System.out.println("Erro ao entrar na sala!");
                    return;
                }
            } else {
                return;
            }
        }

        while (true) {
            System.out.println("\n=== Sala: " + sala.getNome() + " ===");
            System.out.println("Código: " + sala.getId());
            System.out.println("Descrição: " + sala.getDescricao());
            System.out.println("Líder: " + sala.getLider().getUsername());

            System.out.println("\n=== Grupos ===");
            List<Grupo> grupos = sistema.listarGruposDaSala(codigoSala);
            uiGrupo.listarGrupos(grupos);

            System.out.println("\n=== Atividades ===");
            List<Atividade> atividades = sistema.listarAtividadesDaSala(codigoSala);
            uiAtividade.listarAtividades(atividades);

            System.out.println("\nO que deseja fazer?");

            if (usuarioEstaNaSala) {
                System.out.println("[1] Criar grupo");
                System.out.println("[2] Ver grupo");
                System.out.println("[3] Criar atividade");
                System.out.println("[4] Ver atividade");
                System.out.println("[5] Ver participantes");
                System.out.println("[6] Sair da sala");

                if (sistema.getUsuarioAtual().getUsername().equals(sala.getLider().getUsername())) {
                    System.out.println("\n=== Gerenciar Sala ===");
                    System.out.println("[7] Atualizar sala");
                    System.out.println("[8] Excluir sala");
                }
            }

            System.out.println("[0] Voltar");

            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1:
                    if (usuarioEstaNaSala) {
                        uiGrupo.adicionar(sala, sistema.getUsuarioAtual());
                    } else {
                        System.out.println("Você precisa estar na sala para criar um grupo!");
                    }
                    break;

                case 2:
                    if (usuarioEstaNaSala) {
                        System.out.println("Insira o código do grupo: ");
                        int codigoGrupo = scn.nextInt();
                        if (sistema.buscarGrupo(codigoGrupo) != null)
                            telaGrupo(codigoGrupo);
                        else System.out.println("Grupo não encontrado!");
                    } else {
                        System.out.println("Você precisa estar na sala para ver um grupo!");
                    }
                    break;

                case 3:
                    if (usuarioEstaNaSala) {
                        uiAtividade.adicionar(sala);
                    } else {
                        System.out.println("Você precisa estar na sala para criar uma atividade!");
                    }
                    break;

                case 4:
                    if (usuarioEstaNaSala) {
                        System.out.print("Digite o código da atividade: ");
                        int idAtividade = scn.nextInt();
                        telaAtividade(idAtividade);
                    } else {
                        System.out.println("Você precisa estar na sala para ver atividades!");
                    }
                    break;

                case 5:
                    uiUsuario.listarUsuarios(sistema.listarParticipantesSala(codigoSala));
                    break;

                case 6:
                    if (usuarioEstaNaSala) {
                        if (sistema.sairSala(sala.getId())) {
                            System.out.println("Saiu da sala com sucesso!");
                            return;
                        } else {
                            System.out.println("Erro ao sair da sala!");
                        }
                    }
                    break;

                case 7:
                    if (usuarioEstaNaSala && sistema.getUsuarioAtual().getUsername().equals(sala.getLider().getUsername())) {
                        atualizarSala(sala);
                    } else {
                        System.out.println("Apenas o líder pode atualizar a sala!");
                    }
                    break;

                case 8:
                    if (usuarioEstaNaSala && sistema.getUsuarioAtual().getUsername().equals(sala.getLider().getUsername())) {
                        excluirSala(sala);
                        return;
                    } else {
                        System.out.println("Apenas o líder pode excluir a sala!");
                    }
                    break;

                case 0:
                    return;
            }
        }
    }

    // ============================================
    // Tela de Grupo
    // ============================================
    private void telaGrupo(int codigoGrupo) {
        limparTela();
        Sistema sistema = Sistema.getInstance();
        Grupo g = sistema.buscarGrupo(codigoGrupo);

        if (g == null) {
            System.out.println("Grupo não encontrado!");
            return;
        }

        boolean isLider = sistema.getUsuarioAtual().getUsername().equals(g.getLider().getUsername());

        while (true) {
            System.out.println("\n=== Grupo: " + g.getNome() + " ===");
            System.out.println("Código: " + g.getId());
            System.out.println("Sala: " + g.getSala().getNome());
            System.out.println("Líder: " + g.getLider().getUsername());

            System.out.println("\n=== Participantes ===");
            List<Usuario> u = sistema.listarParticipantesGrupo(codigoGrupo);
            uiUsuario.listarUsuarios(u);

            System.out.println("\nO que deseja fazer?");

            // todos membros do grupo
            System.out.println("[1] Sair do grupo");
            System.out.println("[0] Voltar");


            // líder
            if (isLider) {
                System.out.println("\n=== Gerenciar Grupo ===");
                System.out.println("[2] Atualizar grupo");
                System.out.println("[3] Excluir grupo");
                System.out.println("[4] Adicionar atividade a membro");
            }

            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1:
                    if (isLider) {
                        System.out.println("O líder não pode sair do grupo. Transfira a liderança primeiro!");
                    } else {
                        uiGrupo.sair(g.getId());
                        return;
                    }
                    break;

                case 2:
                    if (isLider) {
                        atualizarGrupo(g);
                    } else {
                        System.out.println("Apenas o líder pode atualizar o grupo!");
                    }
                    break;

                case 3:
                    if (isLider) {
                        excluirGrupo(g);
                        return;
                    } else {
                        System.out.println("Apenas o líder pode excluir o grupo!");
                    }
                    break;

                case 4:
                    if (isLider) {
                        atribuirAtividadeAMembro(g);
                    } else {
                        System.out.println("Apenas o líder pode atribuir atividades!");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void atualizarGrupo(Grupo grupo) {
        System.out.println("\n=== Atualizar Grupo ===");
        System.out.println("O que deseja atualizar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Transferir liderança");
        System.out.println("[0] Voltar");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                String novoNome = scn.nextLine();
                if (novoNome != null && !novoNome.isEmpty()) {
                    if (sistema.atualizarNomeGrupo(grupo.getId(), novoNome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar nome!");
                    }
                }
                break;

            case 2:
                System.out.println("Digite o username do novo líder:");
                String novoLider = scn.nextLine();
                if (novoLider != null && !novoLider.isEmpty()) {
                    if (!sistema.usuarioEstaNoGrupo(grupo.getId(), novoLider)) {
                        System.out.println("O novo líder deve ser um membro do grupo!");
                        return;
                    }
                    if (sistema.atualizarLiderGrupo(grupo.getId(), novoLider)) {
                        System.out.println("Liderança transferida com sucesso!");
                    } else {
                        System.out.println("Erro ao transferir liderança!");
                    }
                }
                break;
        }
    }

    private void excluirGrupo(Grupo grupo) {
        System.out.println("\n=== Excluir Grupo ===");
        System.out.println("Tem certeza que deseja excluir o grupo '" + grupo.getNome() + "'?");
        System.out.println("Esta ação não pode ser desfeita!");
        System.out.println("Digite 'CONFIRMAR' para excluir:");

        String confirmacao = scn.nextLine();

        if (confirmacao.equals("CONFIRMAR")) {
            if (sistema.excluirGrupo(grupo.getId())) {
                System.out.println("Grupo excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir grupo!");
            }
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    private void atribuirAtividadeAMembro(Grupo grupo) {
        System.out.println("\n=== Atribuir Atividade a Membro ===");

        System.out.println("Digite o código da atividade:");
        int codigoAtividade = scn.nextInt();
        scn.nextLine();

        Atividade atividade = sistema.buscarAtividade(codigoAtividade);
        if (atividade == null) {
            System.out.println("Atividade não encontrada!");
            return;
        }

        System.out.println("Digite o username do membro:");
        String username = scn.nextLine();

        if (!sistema.usuarioExiste(username)) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        if (!sistema.usuarioEstaNoGrupo(grupo.getId(), username)) {
            System.out.println("Este usuário não é membro do grupo!");
            return;
        }

        if (sistema.atividadeJaAtribuida(codigoAtividade, username)) {
            System.out.println("Esta atividade já está atribuída a este usuário!");
            return;
        }

        if (sistema.adicionarAtividadeParaAluno(codigoAtividade, username)) {
            System.out.println("Atividade atribuída com sucesso!");
        } else {
            System.out.println("Erro ao atribuir atividade!");
        }
    }

    private void atualizarSala(Sala sala) {
        System.out.println("\n=== Atualizar Sala ===");
        System.out.println("O que deseja atualizar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Descrição");
        System.out.println("[0] Voltar");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                String novoNome = scn.nextLine();
                if (novoNome != null && !novoNome.isEmpty()) {
                    if (sistema.atualizarNomeSala(sala.getId(), novoNome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar nome!");
                    }
                }
                break;

            case 2:
                System.out.println("Digite a nova descrição:");
                String novaDescricao = scn.nextLine();
                if (novaDescricao != null && !novaDescricao.isEmpty()) {
                    if (sistema.atualizarDescricaoSala(sala.getId(), novaDescricao)) {
                        System.out.println("Descrição atualizada com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar descrição!");
                    }
                }
                break;
        }
    }

    private void excluirSala(Sala sala) {
        System.out.println("\n=== Excluir Sala ===");
        System.out.println("Tem certeza que deseja excluir a sala '" + sala.getNome() + "'?");
        System.out.println("Esta ação não pode ser desfeita!");
        System.out.println("Digite 'CONFIRMAR' para excluir:");

        String confirmacao = scn.nextLine();

        if (confirmacao.equals("CONFIRMAR")) {
            if (sistema.excluirSala(sala.getId())) {
                System.out.println("Sala excluída com sucesso!");
            } else {
                System.out.println("Erro ao excluir sala!");
            }
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    // ============================================
    // Tela de Login e Principal
    // ============================================
    private boolean mostrarTelaLogin() {
        System.out.println("\n[1] Entrar");
        System.out.println("[2] Criar conta");
        System.out.println("[0] Sair");

        System.out.println("\nO que deseja fazer?");
        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                uiUsuario.login();
                return true;

            case 2:
                uiUsuario.cadastrar();
                return true;

            case 0:
                return false;
        }
        return true;
    }

    private boolean mostrarTelaPrincipal() {
        Sistema sistema = Sistema.getInstance();
        mostrarCabecalho();

        System.out.println("\n=== Minhas Salas ===");
        List<Sala> salas = sistema.listarSalasDoUsuario();
        uiSala.listar(salas);

        System.out.println("\nO que deseja fazer?");
        System.out.println("[1] Ver sala");
        System.out.println("[2] Entrar em sala");
        System.out.println("[3] Criar sala");
        System.out.println("[4] Meu perfil");
        System.out.println("[0] Sair");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 0:
                uiUsuario.logout();
                return false;

            case 1:
                System.out.print("Digite o código da sala: ");
                int codigoSalaVer = scn.nextInt();
                telaSala(codigoSalaVer);
                return true;

            case 2:
                System.out.println("Digite o código da sala: ");
                int codigoSalaEntrar = scn.nextInt();
                uiUsuario.entrarSala(codigoSalaEntrar);
                return true;

            case 3:
                uiSala.adicionar();
                return true;

            case 4:
                uiUsuario.telaPerfil();
                return true;
        }
        return true;
    }

    // ============================================
    // Tela de Atividade
    // ============================================
    public void telaAtividade(int idAtividade) {
        limparTela();
        Sistema sistema = Sistema.getInstance();
        Atividade atividade = sistema.buscarAtividade(idAtividade);

        if (atividade == null) {
            System.out.println("Atividade não encontrada!");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("\n=== Atividade: " + atividade.getNome() + " ===");
            System.out.println("Código: " + atividade.getId());
            System.out.println("Descrição: " + atividade.getDescricao());
            System.out.println("Data de Entrega: " + sdf.format(atividade.getDataEntrega()));
            System.out.println("Matéria: " + atividade.getMateria());
            System.out.println("Valor: " + atividade.getValor());

            System.out.println("\nO que deseja fazer?");
            System.out.println("[1] Adicionar atividade à minha lista");
            System.out.println("[2] Atualizar atividade");
            System.out.println("[3] Excluir atividade");
            System.out.println("[0] Voltar");

            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1:
                    uiUsuario.adicionarAtividade(atividade.getId(), sistema.getUsuarioAtual().getUsername());
                    break;

                case 2:
                    uiAtividade.atualizar(atividade.getId());
                    break;

                case 3:
                    System.out.println("Tem certeza que deseja excluir esta atividade? (S/N)");
                    String confirmacao = scn.nextLine();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        if (sistema.excluirAtividade(idAtividade)) {
                            System.out.println("Atividade excluída com sucesso!");
                            return;
                        } else {
                            System.out.println("Erro ao excluir atividade!");
                        }
                    }
                    break;

                case 0:
                    return;
            }
        }
    }

    // ============================================
    // Métodos auxiliares
    // ============================================
    private void mostrarCabecalho() {
        Sistema sistema = Sistema.getInstance();
        Usuario atual = sistema.getUsuarioAtual();
        System.out.println("\nBem-vindo(a), " + atual.getUsername() + "!");
    }

    private void limparTela() {
        System.out.println("\n\n");
        System.out.println("=".repeat(50));
        System.out.println("\n");
    }
}