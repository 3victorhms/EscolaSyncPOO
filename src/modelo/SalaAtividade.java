package modelo;

public class SalaAtividade {
    protected Sala sala;
    protected Atividade atividade;

    protected SalaAtividade(Sala sala, Atividade atividade) {
        this.sala = sala;
        this.atividade = atividade;
    }

    public static SalaAtividade getInstance(Sala sala, Atividade atividade) {
        if (sala == null || atividade == null) return null;
        return new SalaAtividade(sala, atividade);
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public Sala getSala() {
        return sala;
    }
}
