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
                        uiSala.atualizar(sala);
                    } else {
                        System.out.println("Apenas o líder pode atualizar a sala!");
                    }
                    break;

                case 8:
                    if (usuarioEstaNaSala && sistema.getUsuarioAtual().getUsername().equals(sala.getLider().getUsername())) {
                        uiSala.excluir(sala);
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
                        uiGrupo.atualizar(g);
                    } else {
                        System.out.println("Apenas o líder pode atualizar o grupo!");
                    }
                    break;

                case 3:
                    if (isLider) {
                        uiGrupo.excluir(g);
                        return;
                    } else {
                        System.out.println("Apenas o líder pode excluir o grupo!");
                    }
                    break;

                case 4:
                    if (isLider) {
                        System.out.println("Digite o username do membro:");
                        String username = scn.nextLine();
                        if (sistema.usuarioExiste(username))
                            if (sistema.usuarioEstaNoGrupo(g.getId(), username)) {
                                System.out.println("Digite o id da atividade:");
                                int idAtividade = scn.nextInt();
                                if (sistema.atividadeExiste(idAtividade)) {
                                    uiUsuario.adicionarAtividade(idAtividade, username);
                                }
                            }
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
                    uiAtividade.excluir(atividade.getId());
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