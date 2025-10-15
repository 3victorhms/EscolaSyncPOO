package ui;

import controle.Sistema;
import modelo.Atividade;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.util.List;
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

    public void logout() {
        if (sistema.logout()) {
            System.out.println("Logout efetuado com sucesso!");
        } else {
            System.out.println("Erro ao fazer logout!");
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

    public void entrarGrupo(int codigoSala) {
        System.out.println("Insira o código do grupo: ");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo > 0) {
            Grupo g = sistema.buscarGrupo(codigo);
            if (g != null) {
                if (sistema.entrarGrupo(g.getId())) {
                    System.out.println("Grupo acessado com sucesso!");
                } else {
                    System.out.println("Erro ao acessar grupo!");
                }
            } else {
                System.out.println("Grupo não encontrado!");
            }
        }
    }

    public void sairSala() {
        System.out.println("Insira o código da sala para sair:");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo > 0) {
            Sala s = sistema.buscarSala(codigo);
            if (s != null) {
                if (sistema.sairSala(codigo)) {
                    if (sistema.removerAtividadesDeAlunoDaSala(codigo)) {
                        if (sistema.removerAlunoDeGrupoDaSala(codigo))
                            System.out.println("Saiu da sala com sucesso!");
                    }
                }
            } else {
                System.out.println("Erro ao sair da sala!");
            }
        } else {
            System.out.println("Código inválido!");
        }
    }

    public void sairGrupo() {
    }


    public void telaPerfil() {
        System.out.println(sistema.getUsuarioAtual().toString());
    }

    public void adicionarAtividade(Atividade atividade) {
        if (sistema.adicionarAtividadeParaAluno(atividade, sistema.getUsuarioAtual())) {
            System.out.println("Atividade adicionada com sucesso!");
        } else {
            System.out.println("Erro ao adicionar atividade!");
        }
    }

    public void removerAtividade(Atividade atividade) {
        if (sistema.removerAtividadeParaAluno(atividade, sistema.getUsuarioAtual())) {
            System.out.println("Atividade removida com sucesso!");
        } else {
            System.out.println("Erro ao remover atividade!");
        }

    }

    public void listarUsuarios(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }
}
