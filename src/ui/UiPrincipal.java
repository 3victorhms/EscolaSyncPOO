package ui;

import controle.Sistema;
import modelo.Atividade;
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
        limparTela();
        Sistema sistema = Sistema.getInstance();
        System.out.println("=== EscolaSync ===");

        if (sistema.getUsuarioAtual() == null) {
            System.out.println("\n[1] Entrar");
            System.out.println("[2] Criar conta");
            System.out.println("[0] Sair");

            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1: uiUsuario.login(); break;
                case 2: uiUsuario.cadastrar(); break;
                case 0: break;
            }
        } else {
            mostrarCabecalho();

            System.out.println("\n=== Minhas Salas ===");
            List<Sala> salas = sistema.listarSalasDoUsuario();

            System.out.println("\nO que deseja fazer?");
            System.out.println("[1] Ver sala");
            System.out.println("[2] Entrar em sala");
            System.out.println("[3] Criar sala");
            System.out.println("[4] Meu perfil");
            System.out.println("[0] Sair");

            int opcao = scn.nextInt();
            scn.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código da sala: ");
                    int codigoSala = scn.nextInt();
                    this.telaSala(codigoSala);
                    break;
                case 2: uiUsuario.entrarSala(); break;
                case 3: uiSala.adicionar(); break;
                case 4: uiUsuario.telaPerfil(); break;
                case 0: uiUsuario.logout(); telaInicial(); break;
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
            sistema.listarGruposDaSala(codigoSala);

            System.out.println("\n=== Atividades ===");
            sistema.listarAtividadesDaSala(codigoSala);

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
                case 1: uiGrupo.adicionar(sala, sistema.getUsuarioAtual()); break;
                case 2: uiGrupo.entrar(); break;
                case 3: uiAtividade.adicionar(sala); break;
                case 4:
                    System.out.print("Digite o ID da atividade: ");
                    int idAtividade = scn.nextInt();
                    telaAtividade(idAtividade);
                    break;
                case 5: sistema.listarParticipantesSala(codigoSala); break;
                case 0: return;
            }
        }
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
            System.out.println("ID: " + atividade.getId());
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