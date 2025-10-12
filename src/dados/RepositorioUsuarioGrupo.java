package dados;

import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;
import modelo.UsuarioGrupo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioGrupo {
    protected List<UsuarioGrupo> usuarioGrupos;

    public RepositorioUsuarioGrupo() {
        usuarioGrupos = new ArrayList<>();
    }

    public List<UsuarioGrupo> getUsuarioGrupos() {
        return usuarioGrupos;
    }

    public boolean entrarGrupo(Usuario usuario, Grupo grupo) {
        return usuarioGrupos.add(UsuarioGrupo.getInstance(usuario, grupo));

    }

    public boolean removerAlunoDeGrupoDaSala(Sala sala) {
        for (UsuarioGrupo usuarioGrupo : this.getUsuarioGrupos()){
            if (usuarioGrupo.getGrupo().getSala().equals(sala))
                this.usuarioGrupos.remove(usuarioGrupo);
        }
        return true;
    }
}
