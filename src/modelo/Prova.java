package modelo;

import java.util.Date;

public class Prova extends Atividade {
    protected boolean temConsulta;

    protected Prova(String nome, String descricao, Date dataEntrega, Date dataConclusao,
                    String materia, double valor, Sala sala, boolean temConsulta) {
        super(nome, descricao, dataEntrega, dataConclusao, materia, valor, sala);
        this.temConsulta = temConsulta;
    }

    public static Prova getInstance(String nome, String descricao, Date dataEntrega, Date dataConclusao, String materia, double valor, Sala sala, boolean temConsulta) {
        // lembrando que dataConclusao pode ser nulo;
        if (nome == null || descricao == null || dataEntrega == null || materia == null || valor <= 0 || sala == null)
            return null;
        return new Prova(nome, descricao, dataEntrega, dataConclusao, materia, valor, sala, temConsulta);
    }

    @Override
    public String getTipo() {
        return "Prova";
    }

    public boolean getTemConsulta() {
        return temConsulta;
    }

    @Override
    public String toString() {
        if (temConsulta) return getTipo() + " - " + super.toString() + " - Com Consulta";
        return getTipo() + " - " + super.toString() + " - Sem Consulta";
    }
}

