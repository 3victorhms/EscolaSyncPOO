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
                    Sala sala = Sala.getInstance(nome, descricao, data, sistema.buscarUsuario(sistema.getUsuarioAtual().getUsername()));
                    if (sistema.adicionarSala(sala))
                        if (sistema.entrarSala(sala.getId()))
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
            System.out.println(">>> Nome inválido!");
        }
    }

    public void excluir() {
        System.out.println("Insira o código da sala:");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo <= 0)
            System.out.println(">>> Código inválido!");
        else if (sistema.salaExiste(codigo)) {
            if (sistema.excluirSala(codigo)) {
                if (sistema.removerAlunosSala(codigo)) {
                    if (sistema.removerAtividadesSala(codigo)) {
                        if (sistema.removerGruposSala(codigo)) {
                            System.out.println("Sala excluída com sucesso!");
                        } else System.out.println("Falha ao remover grupos da sala!");
                    }
                }
            } else
                System.out.println("Sala não encontrada!");
        }
    }

    public void atualizar() {
        System.out.println("Insira o código da sala:");
        int codigo = scn.nextInt();
        scn.nextLine();
        if (codigo <= 0)
            System.out.println(">>> Código inválido!");
        else if (sistema.salaExiste(codigo)) {
            System.out.println();
        }
    }

    public void listar(List<Sala> salas) {
        if (salas.isEmpty()) {
            System.out.println("\nNenhuma sala encontrada.");
            return;
        }

        System.out.println("\n=== Lista de Salas ===");
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