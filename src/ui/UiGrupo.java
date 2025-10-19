package ui;

import controle.Sistema;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.util.List;
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

    public void excluir(int codigo) {
        if (sistema.excluirGrupo(codigo)) {
            System.out.println("Grupo excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir grupo!");
        }
    }

    public void atualizar(int codigo) {
        System.out.println("\n O que você deseja atualizar? \n");
        System.out.println("[1] Nome");
        System.out.println("[2] Líder");
        System.out.println("[0] Voltar");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Insira o novo nome do grupo:");
                String nome = scn.nextLine();
                if (nome != null && !nome.isEmpty()) {
                    if (sistema.atualizarNomeGrupo(codigo, nome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    }
                }
                break;
            case 2:
                System.out.println("Insira o username do novo líder do grupo:");
                String lider = scn.nextLine();
                if (lider != null && !lider.isEmpty() && sistema.usuarioExiste(lider)) {
                    if (sistema.atualizarLiderGrupo(codigo, lider)) {
                        System.out.println("Líder atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar líder!");
                    }
                }
                break;
            case 0:
                break;
        }

    }

    public void entrar() {
        System.out.println("Insira o código do grupo:");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo <= 0)
            System.out.println("Código inválido!");
        else if (!sistema.grupoExiste(codigo)) {
            System.out.println("Grupo não encontrado!");
        } else if (!sistema.usuarioEstaNoGrupo(codigo, sistema.getUsuarioAtual().getUsername())) {
            if (sistema.entrarGrupo(codigo)) {
                System.out.println("Entrou no grupo com sucesso!");
            } else {
                System.out.println("Erro ao acessar grupo!");
            }
        } else System.out.println("Você já está no grupo!");

    }

    public void sair(int codigo) {
        if (sistema.sairGrupo(codigo)) {
            System.out.println("Saiu do grupo realizado com sucesso!");
        } else {
            System.out.println("Erro ao sair do grupo!");
        }
    }

    public void listarGrupos(List<Grupo> grupos) {
        for (Grupo grupo : grupos)
            System.out.println(grupo.toString());
    }
}
