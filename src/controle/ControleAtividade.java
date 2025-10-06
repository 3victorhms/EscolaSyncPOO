package controle;

import dados.RepositorioAtividade;
import modelo.Atividade;

public class ControleAtividade {
    protected RepositorioAtividade repositorioAtividade;

    protected ControleAtividade() {
        repositorioAtividade = new RepositorioAtividade();
    }

    public void adicionar(Atividade atividade) {
        repositorioAtividade.adicionar(atividade);
    }

    public boolean remover(Atividade atividade) {
        return repositorioAtividade.remover(atividade);
    }
}
