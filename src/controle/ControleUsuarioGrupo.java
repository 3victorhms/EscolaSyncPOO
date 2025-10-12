package controle;

import dados.RepositorioUsuarioGrupo;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

public class ControleUsuarioGrupo {
    protected RepositorioUsuarioGrupo repositorioUsuarioGrupo;

    protected ControleUsuarioGrupo() {
        repositorioUsuarioGrupo = new RepositorioUsuarioGrupo();
    }

    public boolean entrarGrupo(Usuario usuario, Grupo grupo) {
        return repositorioUsuarioGrupo.entrarGrupo(usuario, grupo);
    }

    public boolean removerAlunoDeGrupoDaSala(Sala sala) {
        return repositorioUsuarioGrupo.removerAlunoDeGrupoDaSala(sala);
    }
}
