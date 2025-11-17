package ui;

import controle.Sistema;
import modelo.Atividade;

import java.util.List;
import java.util.Scanner;

public class UiAtividade {

    private final Scanner scn;
    private final Sistema sistema;

    public UiAtividade() {
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
    }

    public void adicionar(int salaId) {

        System.out.println("Qual o tipo da atividade?");
        System.out.println("[1] Tarefa");
        System.out.println("[2] Prova");
        int tipo = Integer.parseInt(scn.nextLine());

        System.out.print("Nome: ");
        String nome = scn.nextLine();

        System.out.print("Descrição: ");
        String descricao = scn.nextLine();

        System.out.print("Data de entrega (dd/MM/yyyy): ");
        String data = scn.nextLine();

        System.out.print("Matéria: ");
        String materia = scn.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scn.nextLine());

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

        System.out.println("O que deseja atualizar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Descrição");
        System.out.println("[3] Data de entrega");
        System.out.println("[4] Status (concluir / desfazer)");
        System.out.println("[5] Matéria");
        System.out.println("[6] Valor");
        System.out.println("[7] Sair");
        System.out.print(">>> ");

        int op = Integer.parseInt(scn.nextLine());

        try {
            switch (op) {
                case 1: {
                    System.out.print("Novo nome: ");
                    sistema.atualizarNomeAtividade(id, scn.nextLine());
                }
                case 2: {
                    System.out.print("Nova descrição: ");
                    sistema.atualizarDescricaoAtividade(id, scn.nextLine());
                }
                case 3: {
                    System.out.print("Nova data (dd/MM/yyyy): ");
                    sistema.atualizarDataEntregaAtividade(id, scn.nextLine());
                }
                case 5: {
                    System.out.print("Nova matéria: ");
                    sistema.atualizarMateriaAtividade(id, scn.nextLine());
                }
                case 6: {
                    System.out.print("Novo valor: ");
                    double valor = Double.parseDouble(scn.nextLine());
                    sistema.atualizarValorAtividade(id, valor);
                }
                case 7:
                    System.out.println("Voltando...");
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
