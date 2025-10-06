package ui;

import controle.Sistema;
import modelo.Sala;
import modelo.Usuario;

import java.util.Random;
import java.util.Scanner;

public class UiSala {
    protected Random rnd;
    protected Scanner scn;
    protected Sistema sistema;

    public UiSala() {
        rnd = new Random();
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
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
                    System.out.println("Insira o username do líder da sala: ");
                    String username = scn.nextLine();
                    if (username != null && !username.isEmpty())
                        if (sistema.adicionarSala(Sala.getInstance(nome, descricao, Data.getInstance(dataCriacao), sistema.buscarUsuario())))
                            System.out.println(">>> Sala criada com sucesso!");
                        else
                            System.out.println(">>> Falha ao criar sala!");
                }
            }
        }
    }
}
