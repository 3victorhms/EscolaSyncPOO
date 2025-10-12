package ui;

import controle.Sistema;
import modelo.Atividade;
import modelo.Sala;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class UiAtividade {
    protected Random rnd;
    protected Scanner scn;
    protected Sistema sistema;
    protected SimpleDateFormat sdf;

    public UiAtividade() {
        rnd = new Random();
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void adicionar(Sala sala) {
        System.out.println("Insira o nome da atividade:");
        String nome = scn.nextLine();
        if (nome != null && !nome.isEmpty()) {
            System.out.println("Insira a descrição da atividade;");
            String descricao = scn.nextLine();
            if (descricao != null && !descricao.isEmpty()) {
                System.out.println("Insira a data de entrega da atividade;");
                String data = scn.nextLine();
                if (data != null && !data.isEmpty()) {
                    Date dataEntrega;
                    try {
                        dataEntrega = sdf.parse(data);
                    } catch (ParseException e) {
                        System.out.println(">>> Data inválida!");
                        return;
                    }
                    System.out.println("Insira a matéria da atividade:");
                    String materia = scn.nextLine();
                    if (materia != null && !materia.isEmpty()) {
                        System.out.println("Insira o valor da atividade:");
                        double valor = scn.nextDouble();
                        if (valor > 0) {
                            sistema.adicionarAtividade(Atividade.getInstance(nome, descricao, dataEntrega, null, materia, valor, sistema.buscarSala(sala.getId())));
                        }
                    }
                }
            }
        }
    }

    public void excluir() {
        System.out.println("Insira o código da atividade:");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo <= 0)
            System.out.println(">>> Código inválido!");
        else if (sistema.excluirAtividade(codigo))
            System.out.println("Atividade excluída com sucesso!");
        else
            System.out.println("Atividade não encontrada!");
    }

    public void atualizar() {
        System.out.println("Insira o código da atividade:");
        int codigo = scn.nextInt();
        scn.nextLine();

        Atividade atividade = sistema.buscarAtividade(codigo);

        if (atividade != null) {

            System.out.println("O que você deseja atualizar?");
            System.out.println("1. Nome");
            System.out.println("2. Descrição");
            System.out.println("3. Data de entrega");
            System.out.println("4. Data de conclusão");
            System.out.println("5. Matéria");
            System.out.println("6. Valor");
            System.out.println("7. Sair");
            System.out.print(">>> Escolha uma opção: ");
            int opcao = scn.nextInt();
            scn.nextLine();
            if (opcao == 1) {
                System.out.println("Insira o novo nome da atividade:");
                String nome = scn.nextLine();
                if (nome != null && !nome.isEmpty())
                    if (sistema.alterarNomeAtividade(codigo, nome))
                        System.out.println(">>> Nome atualizado com sucesso!");
                    else
                        System.out.println(">>> Erro ao atualizar nome!");
                else
                    System.out.println(">>> Nome inválido!");
            } else if (opcao == 2) {
                System.out.println("Insira a nova descrição da atividade:");
                String descricao = scn.nextLine();
                if (descricao != null && !descricao.isEmpty())
                    if (sistema.alterarDescricaoAtividade(codigo, descricao))
                        System.out.println(">>> Descrição atualizada com sucesso!");
                    else
                        System.out.println(">>> Erro ao atualizar descrição!");
                else
                    System.out.println(">>> Descrição inválida!");
            } else if (opcao == 3) {
                System.out.println("Insira a nova data de entrega da atividade:");
                String data = scn.nextLine();
                if (data != null && !data.isEmpty()) {
                    Date dataEntrega = null;
                    try {
                        dataEntrega = sdf.parse(data);
                    } catch (ParseException e) {
                        System.out.println(">>> Data inválida!");
                    }
                    if (dataEntrega != null)
                        if (sistema.alterarDataEntregaAtividade(codigo, dataEntrega))
                            System.out.println(">>> Data de entrega atualizada com sucesso!");
                        else System.out.println(">>> Erro ao atualizar data de entrega!");
                    else System.out.println(">>> Data inválida!");
                }
            } else if (opcao == 4) {
                System.out.println("Insira a nova data de conclusão da atividade:");
                String data = scn.nextLine();
                if (data != null && !data.isEmpty()) {
                    Date dataConclusao = null;
                    try {
                        dataConclusao = sdf.parse(data);
                    } catch (ParseException e) {
                        System.out.println(">>> Data inválida!");
                    }
                    if (dataConclusao != null)
                        if (sistema.alterarDataConclusaoAtividade(codigo, dataConclusao))
                            System.out.println(">>> Data de conclusão atualizada com sucesso!");
                        else System.out.println(">>> Erro ao atualizar data de conclusão!");
                    else System.out.println(">>> Data inválida!");
                }
            } else if (opcao == 5) {
                System.out.println("Insira a nova matéria referente à atividade:");
                String materia = scn.nextLine();
                if (materia != null && !materia.isEmpty())
                    if (sistema.alterarMateriaAtividade(codigo, materia))
                        System.out.println(">>> Matéria atualizada com sucesso!");
                    else System.out.println(">>> Erro ao atualizar matéria!");
                else System.out.println(">>> Matéria inválida!");
            } else if (opcao == 6) {
                System.out.println("Insira o novo valor da atividade:");
                double valor = scn.nextDouble();
                if (valor >= 0)
                    if (sistema.alterarValorAtividade(codigo, valor))
                        System.out.println(">>> Valor atualizado com sucesso!");
                    else System.out.println(">>> Erro ao atualizar valor!");
                else System.out.println(">>> Valor inválido!");
            } else if (opcao == 7) {
                System.out.println("Voltando ao menu principal...");
            }
        } else {
            System.out.println(">>> Código inválido!");
        }
    }

}
