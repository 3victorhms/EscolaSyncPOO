package modelo;

import java.util.Date;

public class Atividade {

    protected int id;
    protected String nome;
    protected String descricao;
    protected Date dataEntrega;
    protected Date dataConclusao;
    protected String materia;
    protected double valor;

    protected static int proxId = 1;

    protected Atividade(String nome, String descricao, Date dataEntrega, Date dataConclusao, String materia, double valor) {
        this.id = proxId++;
        this.nome = nome;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.dataConclusao = dataConclusao;
        this.materia = materia;
        this.valor = valor;
    }

    public static Atividade getInstance(String nome, String descricao, Date dataEntrega, Date dataConclusao, String materia, double valor) {
        return new Atividade(nome, descricao, dataEntrega, dataConclusao, materia, valor);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
