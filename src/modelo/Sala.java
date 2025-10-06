package modelo;

import java.util.Date;

public class Sala {
    protected int id;
    protected String nome;
    protected String descricao;
    protected Date dataCriacao;
    protected Usuario lider;

    protected static int proxId = 1;

    protected Sala(String nome, String descricao, Date dataCriacao, Usuario lider) {
        this.id = proxId++;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.lider = lider;
    }

    public static Sala getInstance(String nome, String descricao, Date dataCriacao, Usuario lider) {
        if (nome == null || descricao == null || dataCriacao == null || lider == null) return null;
        return new Sala(nome, descricao, dataCriacao, lider);
    }
}
