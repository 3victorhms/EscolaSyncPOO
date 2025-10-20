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

    public boolean sairGrupo(Usuario usuarioAtual, Grupo grupo) {
        for (UsuarioGrupo usuarioGrupo : this.getUsuarioGrupos()){
            if (usuarioGrupo.getUsuario().equals(usuarioAtual) && usuarioGrupo.getGrupo().equals(grupo))
                this.usuarioGrupos.remove(usuarioGrupo);
        }
        return true;
    }

    public boolean removerAlunoDeGrupoDaSala(Sala sala) {
        for (UsuarioGrupo usuarioGrupo : this.getUsuarioGrupos()){
            if (usuarioGrupo.getGrupo().getSala().equals(sala))
                this.usuarioGrupos.remove(usuarioGrupo);
        }
        return true;
    }

    public List<Usuario> listarParticipantesGrupo(Grupo grupo) {
        List<Usuario> participantes = new ArrayList<>();
        for (UsuarioGrupo usuarioGrupo : this.getUsuarioGrupos()) {
            if (usuarioGrupo.getGrupo().equals(grupo))
                participantes.add(usuarioGrupo.getUsuario());
        }
        return participantes;
    }

    public boolean usuarioEstaNoGrupo(Usuario usuarioAtual, Grupo grupo) {
        for (UsuarioGrupo usuarioGrupo : this.getUsuarioGrupos()) {
            if (usuarioGrupo.getUsuario().getUsername().equals(usuarioAtual.getUsername()) && usuarioGrupo.getGrupo().getId() == grupo.getId())
                return true;
        }
        return false;
    }
}
