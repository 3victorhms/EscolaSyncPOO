package ui;

import controle.Sistema;
import modelo.Sala;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class UiSala {
    protected Random rnd;
    protected Scanner scn;
    protected Sistema sistema;
    protected SimpleDateFormat sdf;


    public UiSala() {
        rnd = new Random();
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void adicionar() {
        System.out.println("Insira o nome da sala: ");
        String nome = scn.nextLine();
        if (nome != null && !nome.isEmpty()) {
            System.out.println("Insira a descrição da sala: ");
            String descricao = scn.nextLine();
            if (descricao != null && !descricao.isEmpty()) {
                System.out.println("Insira a data de criação da sala: ");
                String dataCriacao = scn.nextLine();
                if (dataCriacao != null && !dataCriacao.isEmpty()) {
                    Date data;
                    try {
                        data = sdf.parse(dataCriacao);
                    } catch (ParseException e) {
                        System.out.println(">>> Data inválida!");
                        return;
                    }

                    System.out.println("Insira o username do líder da sala: ");
                    String username = scn.nextLine();
                    if (username != null && !username.isEmpty())
                        if (sistema.adicionarSala(Sala.getInstance(nome, descricao, data, sistema.buscarUsuario(username))))
                            System.out.println(">>> Sala criada com sucesso!");
                        else
                            System.out.println(">>> Falha ao criar sala!");
                } else {
                    System.out.println(">>> Data inválida!");
                }
            } else {
                System.out.println(">>> Descrição inválida!");
            }
        } else {
            System.out.println(">>> Username inválido!");
        }
    }
}
