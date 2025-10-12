package dados;

import modelo.Grupo;
import modelo.Usuario;
import modelo.UsuarioGrupo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioGrupo {
    protected List<UsuarioGrupo> usuarioGrupos;

    public RepositorioUsuarioGrupo() {
        usuarioGrupos = new ArrayList<>();
    }

    public boolean entrarGrupo(Usuario usuario, Grupo grupo) {
        return usuarioGrupos.add(UsuarioGrupo.getInstance(usuario, grupo));

    }
}
