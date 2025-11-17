package dados;

import modelo.Atividade;
import modelo.Sala;
import modelo.SalaAtividade;

import java.util.List;
import java.util.ArrayList;

public class RepositorioSalaAtividade {
    List<SalaAtividade> salasAtividades;

    public RepositorioSalaAtividade() {
        salasAtividades = new ArrayList<>();
    }

    public List<SalaAtividade> getSalasAtividades() {
        return salasAtividades;
    }

    public boolean adicionar(SalaAtividade salaAtividade) {
        return salasAtividades.add(salaAtividade);
    }

    public boolean remover(SalaAtividade salaAtividade) {
        return salasAtividades.remove(salaAtividade);
    }

    public boolean removerAtividadesSala(Sala sala) {
        List<SalaAtividade> aRemover = new ArrayList<>();
        for (SalaAtividade salaAtividade : this.getSalasAtividades()) {
            if (salaAtividade.getSala().equals(sala))
                aRemover.add(salaAtividade);
        }
        for (SalaAtividade salaAtividade : aRemover) {
            this.remover(salaAtividade);
        }
        return true;
    }

    public List<Atividade> listarAtividadesDaSala(Sala sala) {
        List<Atividade> atividades = new ArrayList<>();
        for (SalaAtividade salaAtividade : this.getSalasAtividades()) {
            if (salaAtividade != null)
                if (salaAtividade.getSala().equals(sala))
                    atividades.add(salaAtividade.getAtividade());
        }
        return atividades;
    }
}
