package controle;

import dados.RepositorioAtividade;
import modelo.Atividade;

public class ControleAtividade {
    protected RepositorioAtividade repositorioAtividade;

    protected ControleAtividade() {
        repositorioAtividade = new RepositorioAtividade();
    }

    public boolean adicionar(Atividade atividade) {
        return repositorioAtividade.adicionar(atividade);
    }

    public boolean remover(Atividade atividade) {
        return repositorioAtividade.remover(atividade);
    }
}
