package controle;

import dados.RepositorioUsuarioGrupo;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.util.List;

public class ControleUsuarioGrupo {
    protected RepositorioUsuarioGrupo repositorioUsuarioGrupo;

    protected ControleUsuarioGrupo() {
        repositorioUsuarioGrupo = new RepositorioUsuarioGrupo();
    }

    public boolean entrarGrupo(Usuario usuario, Grupo grupo) {
        return repositorioUsuarioGrupo.entrarGrupo(usuario, grupo);
    }

    public boolean sairGrupo(Usuario usuarioAtual, Grupo grupo) {
        return repositorioUsuarioGrupo.sairGrupo(usuarioAtual, grupo);
    }

    public boolean removerAlunoDeGrupoDaSala(Sala sala) {
        return repositorioUsuarioGrupo.removerAlunoDeGrupoDaSala(sala);
    }

    public List<Usuario> listarParticipantesGrupo(Grupo grupo) {
        return repositorioUsuarioGrupo.listarParticipantesGrupo(grupo);
    }

    public boolean usuarioEstaNoGrupo(Usuario usuarioAtual, Grupo grupo) {
        return repositorioUsuarioGrupo.usuarioEstaNoGrupo(usuarioAtual, grupo);
    }
}
