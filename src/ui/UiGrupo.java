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

    public void excluir(Grupo grupo) {
        System.out.println("\n=== Excluir Grupo ===");
        System.out.println("Tem certeza que deseja excluir o grupo '" + grupo.getNome() + "'?");
        System.out.println("Esta ação não pode ser desfeita!");
        System.out.println("Digite 'CONFIRMAR' para excluir:");

        String confirmacao = scn.nextLine();

        if (confirmacao.equals("CONFIRMAR")) {
            if (sistema.excluirGrupo(grupo.getId())) {
                System.out.println("Grupo excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir grupo!");
            }
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    public void atualizar(Grupo grupo) {
        System.out.println("\n=== Atualizar Grupo ===");
        System.out.println("O que deseja atualizar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Transferir liderança");
        System.out.println("[0] Voltar");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                String novoNome = scn.nextLine();
                if (novoNome != null && !novoNome.isEmpty()) {
                    if (sistema.atualizarNomeGrupo(grupo.getId(), novoNome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar nome!");
                    }
                }
                break;

            case 2:
                System.out.println("Digite o username do novo líder:");
                String novoLider = scn.nextLine();
                if (novoLider != null && !novoLider.isEmpty()) {
                    if (!sistema.usuarioEstaNoGrupo(grupo.getId(), novoLider)) {
                        System.out.println("O novo líder deve ser um membro do grupo!");
                        return;
                    }
                    if (sistema.atualizarLiderGrupo(grupo.getId(), novoLider)) {
                        System.out.println("Liderança transferida com sucesso!");
                    } else {
                        System.out.println("Erro ao transferir liderança!");
                    }
                }
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
