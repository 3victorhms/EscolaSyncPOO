package ui;

import controle.Sistema;
import modelo.Atividade;

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

    public void adicionar() {
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
                            sistema.adicionarAtividade(Atividade.getInstance(nome, descricao, "Não iniciado", dataEntrega, null, materia, valor));
                        }
                    }
                }
            }
        }
    }

    public void excluir() {
        System.out.println("Insira o código da atividade:");
        int codigo = scn.nextInt();
        sistema.excluirAtividade(codigo);
    }


}
