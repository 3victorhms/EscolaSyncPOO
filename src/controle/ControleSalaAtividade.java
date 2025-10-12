package controle;

import dados.RepositorioSalaAtividade;
import modelo.Sala;
import modelo.SalaAtividade;

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
}
