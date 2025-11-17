package modelo;

import java.util.Date;

public class Tarefa extends Atividade {
    protected String tipoTarefa;

    protected Tarefa(String nome, String descricao, Date dataEntrega, Date dataConclusao, String materia, double valor, Sala sala, String tipo) {
        super(nome, descricao, dataEntrega, dataConclusao, materia, valor, sala);
        this.tipoTarefa = tipo;
    }

    public static Tarefa getInstance(String nome, String descricao, Date dataEntrega, Date dataConclusao, String materia, double valor, Sala sala, String tipo) {
        // lembrando que dataConclusao pode ser nulo;
        if (nome == null || descricao == null || dataEntrega == null || materia == null || valor <= 0 || sala == null || tipo == null)
            return null;
        return new Tarefa(nome, descricao, dataEntrega, dataConclusao, materia, valor, sala, tipo);
    }

    @Override
    public String getTipo() {
        return "Tarefa";
    }

    public String getTipoTarefa() {
        return tipoTarefa;
    }

    @Override
    public String toString() {
        return getTipo() + " - " + super.toString() + " - Tipo: " + tipoTarefa;
    }
}
