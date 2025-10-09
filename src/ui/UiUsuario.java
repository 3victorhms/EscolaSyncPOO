package ui;

import controle.Sistema;
import modelo.Usuario;

import java.util.Random;
import java.util.Scanner;

public class UiUsuario {
    protected Random rnd;
    protected Scanner scn;
    protected Sistema sistema;

    public UiUsuario() {
        rnd = new Random();
        scn = new Scanner(System.in);
        sistema = Sistema.getInstance();
    }

    public void login() {
        System.out.print("Digite seu username: ");
        String username = scn.nextLine();
        System.out.print("Digite sua senha: ");
        String password = scn.nextLine();
        if (sistema.login(Usuario.getInstance(username, password))) {
            System.out.println("Login efetuado com sucesso!");
        } else {
            System.out.println("Login falhou!");
        }
    }

    public void cadastrar() {
        System.out.println("Insira seu username: ");
        String username = scn.nextLine();
        System.out.println("Insira sua senha: ");
        String password = scn.nextLine();
        if (sistema.cadastrar(Usuario.getInstance(username, password))) {
            System.out.println("Cadastro efetuado com sucesso!");
        } else {
            System.out.println("Cadastro falhou!");
        }
    }

    public void entrarSala() {
        System.out.println("Insira o ID da sala: ");
        int id = scn.nextInt();
        scn.nextLine();
        if (sistema.entrarSala(id)) {
            System.out.println("Sala acessada com sucesso!");
        } else {
            System.out.println("Sala não encontrada!");
        }
    }

    public void entrarGrupo() {

    }

    public void atribuirAtividade() {
    }

    public void sairSala() {
    }

    public void sairGrupo() {
    }

    public void removerAtribuicao() {
    }
}
