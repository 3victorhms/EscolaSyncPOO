package modelo;

import java.util.Date;

public class Atividade {

    protected int id;
    protected String nome;
    protected String descricao;
    protected String status;
    protected Date dataEntrega;
    protected Date dataConclusao;
    protected String materia;
    protected double valor;

    protected static int proxId = 1;

    protected Atividade(String nome, String descricao, String status, Date dataEntrega, Date dataConclusao, String materia, double valor) {
        this.id = proxId++;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.dataEntrega = dataEntrega;
        this.dataConclusao = dataConclusao;
        this.materia = materia;
        this.valor = valor;
    }

    public static Atividade getInstance(String nome, String descricao, String status, Date dataEntrega, Date dataConclusao, String materia, double valor) {
        return new Atividade(nome, descricao, status, dataEntrega, dataConclusao, materia, valor);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public String getMateria() {
        return materia;
    }

    public double getValor() {
        return valor;
    }
}
