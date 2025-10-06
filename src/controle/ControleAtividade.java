package controle;

import dados.RepositorioAtividade;

public class ControleAtividade {
    protected RepositorioAtividade repositorioAtividade;

    protected ControleAtividade() {
        repositorioAtividade = new RepositorioAtividade();
    }
}
