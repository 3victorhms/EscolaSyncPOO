package dados;

import modelo.UsuarioAtividade;

import java.util.List;

public class RepositorioUsuarioAtividade {
    protected List<UsuarioAtividade> usuarioAtividades;

    public boolean atribuir(UsuarioAtividade usuarioAtividade) {
        return usuarioAtividades.add(usuarioAtividade);
    }

    public boolean remover(UsuarioAtividade usuarioAtividade) {
        return usuarioAtividades.remove(usuarioAtividade);
    }
}
