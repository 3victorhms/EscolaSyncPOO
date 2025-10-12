package controle;

import dados.RepositorioSalaAtividade;
import modelo.Atividade;
import modelo.Sala;
import modelo.SalaAtividade;

import java.util.List;

public class ControleSalaAtividade {
    protected RepositorioSalaAtividade repositorioSalaAtividade;

    protected ControleSalaAtividade() {
        repositorioSalaAtividade = new RepositorioSalaAtividade();
    }
    public boolean adicionar(SalaAtividade salaAtividade) {
        return repositorioSalaAtividade.adicionar(salaAtividade);
    }

    public boolean remover(SalaAtividade salaAtividade) {
        return repositorioSalaAtividade.remover(salaAtividade);
    }

    public boolean removerAtividadesSala(Sala sala) {
        return repositorioSalaAtividade.removerAtividadesSala(sala);
    }

    public List<Atividade> listarAtividadesDaSala(Sala sala) {
        return repositorioSalaAtividade.listarAtividadesDaSala(sala);
    }
}
