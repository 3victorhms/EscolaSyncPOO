package ui;

import controle.Sistema;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.util.Random;
import java.util.Scanner;

public class UiGrupo {
    protected Random rnd;
    protected Scanner scn;
    protected Sistema sistema;

    public UiGrupo() {
        rnd = new Random();
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
    }

    public void adicionar(Sala sala, Usuario usuario) {
        System.out.println("Insira o nome do grupo:");
        String nome = scn.nextLine();
        if (nome != null && !nome.isEmpty()) {
            if (sistema.adicionarGrupo(Grupo.getInstance(nome, sala, usuario))) {
                System.out.println("Grupo adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar grupo!");
            }
        } else {
            System.out.println("Erro ao adicionar grupo!");
        }
    }

    public void excluir() {
    }

    public void entrar() {
        System.out.println("Insira o código do grupo:");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo <= 0)
            System.out.println(">>> Código inválido!");
        else if (sistema.entrarGrupo(codigo)) {
            System.out.println("Entrou no grupo com sucesso!");
        } else {
            System.out.println("Erro ao acessar grupo!");
        }

    }

    public void sair() {}
}
