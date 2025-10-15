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
    protected UiUsuario uiUsuario;
    protected UiSala uiSala;
    protected UiGrupo uiGrupo;
    protected UiAtividade uiAtividade;

    protected Scanner scn = new Scanner(System.in);

    public UiPrincipal() {
        uiUsuario = new UiUsuario();
        uiSala = new UiSala();
        uiGrupo = new UiGrupo();
        uiAtividade = new UiAtividade();
    }

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

    public void telaSala(int codigoSala) {
        limparTela();
        Sistema sistema = Sistema.getInstance();
        Sala sala = sistema.buscarSala(codigoSala);
        if (sala == null) {
            System.out.println("Sala não encontrada!");
            return;
        }

        while (true) {
            System.out.println("\n=== Sala: " + sala.getNome() + " ===");
            System.out.println("Código: " + sala.getId());

            System.out.println("\n=== Grupos ===");
            List<Grupo > g = sistema.listarGruposDaSala(codigoSala);
            uiGrupo.listarGrupos(g);

            System.out.println("\n=== Atividades ===");
            List<Atividade> a = sistema.listarAtividadesDaSala(codigoSala);
            uiAtividade.listarAtividades(a);

            System.out.println("\nO que deseja fazer?");
            System.out.println("[1] Criar grupo");
            System.out.println("[2] Entrar em grupo");
            System.out.println("[3] Criar atividade");
            System.out.println("[4] Ver atividade");
            System.out.println("[5] Ver participantes");
            System.out.println("[6] Ver grupo");
            System.out.println("[0] Voltar");

            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1:
                    uiGrupo.adicionar(sala, sistema.getUsuarioAtual());
                    break;
                case 2:
                    uiGrupo.entrar();
                    break;
                case 3:
                    uiAtividade.adicionar(sala);
                    break;
                case 4:
                    System.out.print("Digite o código da atividade: ");
                    int idAtividade = scn.nextInt();
                    telaAtividade(idAtividade);
                    break;
                case 5:
                    uiUsuario.listarUsuarios(sistema.listarParticipantesSala(codigoSala));
                    break;
                case 6:
                    System.out.print("Digite o código do grupo: ");
                    int codigoGrupo = scn.nextInt();
                    telaGrupo(codigoGrupo);
                case 0:
                    return;
            }
        }
    }

    private void telaGrupo(int codigoGrupo) {
        limparTela();
        Sistema sistema = Sistema.getInstance();
        Grupo g = sistema.buscarGrupo(codigoGrupo);
        System.out.println("\n=== Grupo: " + g.getNome() + " ===");
        System.out.println("Código: " + g.getId());
        System.out.println("Sala: " + g.getSala().getNome());
        System.out.println("Líder: " + g.getLider().getUsername());

        List<Usuario> u = sistema.listarParticipantesGrupo(codigoGrupo);
        uiUsuario.listarUsuarios(u);

        System.out.println("\nO que deseja fazer?");
        System.out.println("[1] Adicionar atividade à membro");
        System.out.println("[2] Atualizar grupo");
        System.out.println("[3] Excluir grupo");
        System.out.println("[4] Sair do grupo");
        System.out.println("[0] Voltar");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                uiAtividade.adicionar(g.getSala());
                break;
            case 2:
                uiGrupo.atualizar(g.getId());
                break;
            case 3:
                uiGrupo.excluir(g.getId());
            case 4:
                uiGrupo.sair(g.getId());
                break;
            case 0:
        }

    }

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

        System.out.println("\nO que deseja fazer?");


        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 0:
                uiUsuario.logout();
                return false;
            case 1:
                System.out.print("Digite o código da sala: ");
                int codigoSala = scn.nextInt();
                telaSala(codigoSala);
                return true;
            case 2:
                uiUsuario.entrarSala();
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
                    uiUsuario.adicionarAtividade(atividade);
                    break;

                case 2:
                    uiAtividade.atualizar();
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
}