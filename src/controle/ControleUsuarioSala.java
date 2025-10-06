package controle;

import dados.RepositorioUsuarioSala;
import modelo.UsuarioSala;

public class ControleUsuarioSala {
    protected RepositorioUsuarioSala repositorioUsuarioSala;

    protected ControleUsuarioSala() {
        repositorioUsuarioSala = new RepositorioUsuarioSala();
    }

    public boolean adicionar(UsuarioSala usuarioSala) {
        return repositorioUsuarioSala.adicionar(usuarioSala);
    }

    public boolean remover(UsuarioSala usuarioSala) {
        return repositorioUsuarioSala.remover(usuarioSala);
    }
}
