package ui;

import controle.Sistema;
import modelo.Atividade;

import java.util.List;
import java.util.Scanner;

public class UiAtividade {

    private final Scanner scn;
    private final Sistema sistema;

    public UiAtividade(Scanner scn) {
        this.scn = scn;
        sistema = Sistema.getInstance();
    }

    public void adicionar(int salaId) {
        int tipo;
        double valor;

        try {
            System.out.println("Qual o tipo da atividade?");
            System.out.println("[1] Tarefa");
            System.out.println("[2] Prova");
            tipo = Integer.parseInt(scn.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Tipo deve ser um número inteiro!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scn.nextLine();

        System.out.print("Descrição: ");
        String descricao = scn.nextLine();

        System.out.print("Data de entrega (dd/MM/yyyy): ");
        String data = scn.nextLine();

        System.out.print("Matéria: ");
        String materia = scn.nextLine();

        try {
            System.out.print("Valor: ");
            valor = Double.parseDouble(scn.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Valor deve ser um número válido!");
            return;
        }

        String extra;
        if (tipo == 1) {
            System.out.print("Tipo da tarefa: ");
            extra = scn.nextLine();
        } else {
            System.out.print("A prova possui consulta? (S/N): ");
            extra = scn.nextLine();
        }

        try {
            sistema.adicionarAtividade(tipo, nome, descricao, data, materia, valor, salaId, extra);
            System.out.println(">>> Atividade adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void excluir(int idAtividade) {
        System.out.print("Tem certeza que deseja excluir? (S/N): ");
        String resp = scn.nextLine();

        if (resp.equalsIgnoreCase("S")) {
            try {
                sistema.excluirAtividade(idAtividade);
                System.out.println(">>> Atividade excluída!");

            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }

    public void atualizar(int id) {
        int op;

        System.out.println("O que deseja atualizar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Descrição");
        System.out.println("[3] Data de entrega");
        System.out.println("[4] Status (concluir / desfazer)");
        System.out.println("[5] Matéria");
        System.out.println("[6] Valor");
        System.out.println("[7] Sair");
        System.out.print(">>> ");

        try {
            op = Integer.parseInt(scn.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Opção deve ser um número inteiro!");
            return;
        }

        try {
            switch (op) {
                case 1: {
                    System.out.print("Novo nome: ");
                    sistema.atualizarNomeAtividade(id, scn.nextLine());
                    break;
                }
                case 2: {
                    System.out.print("Nova descrição: ");
                    sistema.atualizarDescricaoAtividade(id, scn.nextLine());
                    break;
                }
                case 3: {
                    System.out.print("Nova data (dd/MM/yyyy): ");
                    sistema.atualizarDataEntregaAtividade(id, scn.nextLine());
                    break;
                }
                case 5: {
                    System.out.print("Nova matéria: ");
                    sistema.atualizarMateriaAtividade(id, scn.nextLine());
                    break;
                }
                case 6: {
                    try {
                        System.out.print("Novo valor: ");
                        double valor = Double.parseDouble(scn.nextLine());
                        sistema.atualizarValorAtividade(id, valor);
                    } catch (NumberFormatException e) {
                        System.out.println("ERRO: Valor deve ser um número válido!");
                        return;
                    }
                    break;
                }
                case 7:
                    System.out.println("Voltando...");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }
            System.out.println(">>> Atualização realizada!");

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void listar(List<Atividade> atividadesFormatadas) {
        for (Atividade a : atividadesFormatadas) {
            System.out.println(a);
        }
    }
}
