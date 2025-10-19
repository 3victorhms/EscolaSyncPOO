package ui;

import controle.Sistema;
import modelo.Sala;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
                Date dataCriacao = new Date();
                Sala sala = Sala.getInstance(nome, descricao, dataCriacao, sistema.buscarUsuario(sistema.getUsuarioAtual().getUsername()));
                if (sistema.adicionarSala(sala))
                    if (sistema.entrarSala(sala.getId()))
                        System.out.println(">>> Sala criada com sucesso!");
                    else
                        System.out.println(">>> Falha ao criar sala!");
            } else {
                System.out.println(">>> Descrição inválida!");
            }
        } else {
            System.out.println(">>> Nome inválido!");
        }
    }

    public void excluir(Sala sala) {
        System.out.println("\n=== Excluir Sala ===");
        System.out.println("Tem certeza que deseja excluir a sala '" + sala.getNome() + "'?");
        System.out.println("Esta ação não pode ser desfeita!");
        System.out.println("Digite 'CONFIRMAR' para excluir:");

        String confirmacao = scn.nextLine();

        if (confirmacao.equals("CONFIRMAR")) {
            if (sistema.excluirSala(sala.getId())) {
                if (sistema.removerAlunosSala(sala.getId()) &&
                        sistema.removerAtividadesSala(sala.getId()) &&
                        sistema.removerGruposSala(sala.getId())) {
                    System.out.println("Sala excluída com sucesso!");
                } else {
                    System.out.println("A sala foi excluída, mas houve erros ao remover alguns elementos!");
                }
            } else {
                System.out.println("Erro ao excluir a sala!");
            }
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    public void atualizar(Sala sala) {
        System.out.println("\n=== Atualizar Sala ===");
        System.out.println("O que deseja atualizar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Descrição");
        System.out.println("[0] Voltar");

        int opcao = scn.nextInt();
        scn.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                String novoNome = scn.nextLine();
                if (novoNome != null && !novoNome.isEmpty()) {
                    if (sistema.atualizarNomeSala(sala.getId(), novoNome)) {
                        System.out.println("Nome atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar nome!");
                    }
                }
                break;

            case 2:
                System.out.println("Digite a nova descrição:");
                String novaDescricao = scn.nextLine();
                if (novaDescricao != null && !novaDescricao.isEmpty()) {
                    if (sistema.atualizarDescricaoSala(sala.getId(), novaDescricao)) {
                        System.out.println("Descrição atualizada com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar descrição!");
                    }
                }
                break;
        }
    }

    public void listar(List<Sala> salas) {
        if (salas.isEmpty()) {
            System.out.println("\nNenhuma sala encontrada.");
            return;
        }

        for (Sala sala : salas) {
            System.out.println("-".repeat(40));
            System.out.println("Código: " + sala.getId());
            System.out.println("Nome: " + sala.getNome());
            System.out.println("Descrição: " + sala.getDescricao());
            System.out.println("Criada em: " + sdf.format(sala.getDataCriacao()));
            System.out.println("Líder: " + sala.getLider().getUsername());
        }
        System.out.println("-".repeat(40));
    }
}