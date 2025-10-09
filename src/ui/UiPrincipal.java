package ui;

import controle.Sistema;

import java.util.Scanner;

public class UiPrincipal {
    protected UiUsuario uiUsuario;
    protected UiSala uiSala;
    protected UiGrupo uiGrupo;
    protected UiAtividade uiAtividade;

    protected Scanner scn = new Scanner(System.in);

    public UiPrincipal() {
        uiUsuario = new UiUsuario();
    }

    public void iniciar() {
        Sistema sistema = Sistema.getInstance();

        int opcaoInicial = 0;
        do {
            opcaoInicial = menuInicial();
            switch (opcaoInicial) {
                case 1:
                    uiUsuario.login();
                    break;
                case 2:
                    uiUsuario.cadastrar();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcaoInicial != 3);

        int opcaoPrincipal = 0;
        do {
            opcaoPrincipal = menuPrincipal();
            switch (opcaoPrincipal) {
                case 1:
                    int opcaoAdicionar = 0;
                    opcaoAdicionar = menuAdicionar();
                    switch (opcaoAdicionar) {
                        case 1:
                            uiSala.adicionar();
                            break;
                        case 2:
                            uiUsuario.entrarSala();
                            break;
                        case 3:
                            uiGrupo.adicionar();
                            break;
                        case 4:
                            uiUsuario.entrarGrupo();
                            break;
                        case 5:
                            uiAtividade.adicionar();
                            break;
                        case 6:
                            uiUsuario.atribuirAtividade();
                            break;
                        case 7:
                            break;
                    }
                    break;
                case 2:
                    int opcaoExcluir = 0;
                    opcaoExcluir = menuExcluir();
                    switch (opcaoExcluir) {
                        case 1:
                            uiSala.excluir();
                        case 2:
                            uiUsuario.sairSala();
                            break;
                        case 3:
                            uiGrupo.excluir();
                            break;
                        case 4:
                            uiUsuario.sairGrupo();
                            break;
                        case 5:
                            uiAtividade.excluir();
                            break;
                        case 6:
                            uiUsuario.removerAtribuicao();
                            break;
                        case 7:
                            break;
                    } case 3:

            }
        } while (opcaoPrincipal != 7);

    }

    public int menuInicial() {
        System.out.println("===\n EscolaSync ===\n");
        System.out.println("1. Login");
        System.out.println("2. Cadastrar");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        return scn.nextInt();
    }

    public int menuPrincipal() {
        System.out.println("===\n EscolaSync ===\n");
        System.out.println("1. Adicionar");
        System.out.println("2. Excluir");
        System.out.println("3. Buscar");
        System.out.println("4. Listar");
        System.out.println("5. Alterar");
        System.out.println("6. Encerrar sessão");
        System.out.println("7. Encerrar programa");
        System.out.print("Escolha uma opção: ");
        return scn.nextInt();
    }

    public int menuAdicionar() {
        System.out.println("===\n EscolaSync ===\n");
        System.out.println("1. Criar sala");
        System.out.println("2. Entrar em sala");
        System.out.println("3. Criar grupo");
        System.out.println("4. Entrar em grupo");
        System.out.println("5. Criar atividade");
        System.out.println("6. Atribuir atividade");
        System.out.println("7. Voltar");
        System.out.print("Escolha uma opção: ");
        return scn.nextInt();
    }

    public int menuExcluir() {
        System.out.println("===\n EscolaSync ===\n");
        System.out.println("1. Excluir Sala");
        System.out.println("2. Sair de sala");
        System.out.println("3. Excluir Grupo");
        System.out.println("4. Sair de grupo");
        System.out.println("5. Excluir Atividade");
        System.out.println("6. Remover atribuição");
        System.out.println("7. Voltar");
        System.out.print("Escolha uma opção: ");
        return scn.nextInt();
    }
}
