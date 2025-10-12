package controle;

import dados.RepositorioUsuarioAtividade;
import modelo.UsuarioAtividade;

public class ControleUsuarioAtividade {
    protected RepositorioUsuarioAtividade repositorioUsuarioAtividade;

    protected ControleUsuarioAtividade() {
        repositorioUsuarioAtividade = new RepositorioUsuarioAtividade();
    }

    public boolean atribuir(UsuarioAtividade usuarioAtividade) {
        return repositorioUsuarioAtividade.atribuir(usuarioAtividade);
    }

    public boolean remover(UsuarioAtividade usuarioAtividade) {
        return repositorioUsuarioAtividade.remover(usuarioAtividade);
    }
}
