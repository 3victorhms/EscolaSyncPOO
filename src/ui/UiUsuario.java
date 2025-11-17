package ui;

import controle.Sistema;
import modelo.Atividade;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.text.SimpleDateFormat;
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

    // autenticação
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

    // relacionados à Sala
    public boolean entrarSala(int salaId) {
        if (sistema.entrarSala(salaId)) {
            System.out.println("Sala acessada com sucesso!");
            return true;
        } else {
            System.out.println("Sala não encontrada!");
        }
        return false;
    }

    public boolean sairSala(int salaId) {
        if (sistema.sairSala(salaId)) {
            if (sistema.removerAtividadesDeAlunoDaSala(salaId)) {
                if (sistema.removerAlunoDeGrupoDaSala(salaId)) {
                    System.out.println("Saiu da sala com sucesso!");
                    return true;
                }
            }
        }
        System.out.println("Erro ao sair da sala!");
        return false;
    }

    // relacionados à Grupo
    public void entrarGrupo(int codigo, String username) {
        if (!sistema.usuarioEstaNoGrupo(codigo, username)) {
            if (sistema.entrarGrupo(codigo)) {
                System.out.println("Entrou no grupo com sucesso!");
            } else {
                System.out.println("Erro ao acessar grupo!");
            }
        } else System.out.println("Você já está no grupo!");
    }

    public void sairGrupo(int codigo) {
        if (sistema.sairGrupo(codigo)) {
            System.out.println("Saiu do grupo realizado com sucesso!");
        } else {
            System.out.println("Erro ao sair do grupo!");
        }
    }

    // relacionados à Atividade
    public void adicionarAtividade(int idAtividade, String username) {
        if (idAtividade > 0 && username != null) {
            if (sistema.adicionarAtividadeParaAluno(idAtividade, username)) {
                System.out.println("Atividade adicionada com sucesso!");
            } else {
                System.out.println("Atividade já adicionada!");
            }
        } else {
            System.out.println("Atividade ou usuário não encontrado!");
        }
    }

    public void removerAtividade(int atividadeId) {
        Atividade atividade = sistema.buscarAtividade(atividadeId);
        if (atividade != null) {
            if (sistema.removerAtividadeParaAluno(atividade, sistema.getUsuarioAtual())) {
                System.out.println("Atividade removida com sucesso!");
            } else {
                System.out.println("Erro ao remover atividade!");
            }
        } else {
            System.out.println("Atividade não encontrada!");
        }
    }

    // outros métodos
    public void listarUsuarios(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }

    public void telaPerfil() {
        Usuario usuario = sistema.getUsuarioAtual();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=====================================");
            System.out.println(" Perfil de: " + usuario.getUsername());
            System.out.println("=====================================");
            System.out.println("\nEscolha uma opção:");
            System.out.println("[1] Minhas Salas");
            System.out.println("[2] Minhas Atividades");
            System.out.println("[3] Meus Grupos");
            System.out.println("[0] Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    mostrarMinhasSalas();
                    break;
                case 2:
                    mostrarMinhasAtividades();
                    break;
                case 3:
                    mostrarMeusGrupos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void mostrarMinhasSalas() {
        List<Sala> salas = sistema.listarSalasDoUsuario();
        System.out.println("\n=== Minhas Salas ===");

        if (salas == null || salas.isEmpty()) {
            System.out.println("Você ainda não está em nenhuma sala.");
        } else {
            System.out.println("Salas que você participa:");
            for (Sala s : salas) {
                System.out.println("-".repeat(40));
                System.out.println("Nome: " + s.getNome());
                System.out.println("ID: " + s.getId());
                System.out.println("Descrição: " + s.getDescricao());
                System.out.println("Líder: " + s.getLider().getUsername());
            }
        }

        System.out.println("\nPressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }

    private void mostrarMinhasAtividades() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Sala> salas = sistema.listarSalasDoUsuario();
        System.out.println("\n=== Minhas Atividades ===");

        if (salas == null || salas.isEmpty()) {
            System.out.println("Você não possui atividades pois não está em nenhuma sala.");
            System.out.println("\nPressione ENTER para continuar...");
            new Scanner(System.in).nextLine();
            return;
        }

        boolean temAtividades = false;

        System.out.println("\nAtividades Pendentes:");
        for (Sala sala : salas) {
            List<Atividade> atividades = sistema.listarAtividadesDaSala(sala.getId());
            for (Atividade a : atividades) {
                if (a.getDataConclusao() == null) {
                    System.out.println("-".repeat(40));
                    System.out.println("Nome: " + a.getNome());
                    System.out.println("Sala: " + sala.getNome());
                    System.out.println("Entrega: " + sdf.format(a.getDataEntrega()));
                    System.out.println("Matéria: " + a.getMateria());
                    System.out.println("Valor: " + a.getValor());
                    temAtividades = true;
                }
            }
        }

        if (!temAtividades) {
            System.out.println("Nenhuma atividade pendente!");
        }

        System.out.println("\nPressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }

    private void mostrarMeusGrupos() {
        List<Sala> salas = sistema.listarSalasDoUsuario();
        System.out.println("\n=== Meus Grupos ===");

        if (salas == null || salas.isEmpty()) {
            System.out.println("Você não participa de nenhum grupo pois não está em nenhuma sala.");
            System.out.println("\nPressione ENTER para continuar...");
            new Scanner(System.in).nextLine();
            return;
        }

        boolean temGrupos = false;

        for (Sala sala : salas) {
            List<Grupo> grupos = sistema.listarGruposDaSala(sala.getId());
            if (grupos != null && !grupos.isEmpty()) {
                for (Grupo g : grupos) {
                    if (sistema.usuarioEstaNoGrupo(g.getId(), sistema.getUsuarioAtual().getUsername())) {
                        System.out.println("-".repeat(40));
                        System.out.println("Nome: " + g.getNome());
                        System.out.println("ID: " + g.getId());
                        System.out.println("Sala: " + sala.getNome());
                        System.out.println("Líder: " + g.getLider().getUsername());
                        temGrupos = true;
                    }
                }
            }
        }

        if (!temGrupos) {
            System.out.println("Você não participa de nenhum grupo!");
        }

        System.out.println("\nPressione ENTER para continuar...");
        new Scanner(System.in).nextLine();
    }
}