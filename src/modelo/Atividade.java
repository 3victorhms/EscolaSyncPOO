package modelo;

public class Atividade {

    protected int id;
    protected String nome;
    protected String descricao;
    protected String status;
    protected Data dataEntrega;
    protected Data dataConclusao;
    protected String materia;
    protected double valor;

    protected static int proxId = 1;

    protected Atividade(String nome, String descricao, String status, Data dataEntrega, Data dataConclusao, String materia, double valor) {
        this.id = proxId++;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.dataEntrega = dataEntrega;
        this.dataConclusao = dataConclusao;
        this.materia = materia;
        this.valor = valor;
    }

    public static Atividade getInstance(String nome, String descricao, String status, Data dataEntrega, Data dataConclusao, String materia, double valor) {
        return new Atividade(nome, descricao, status, dataEntrega, dataConclusao, materia, valor);
    }
}
