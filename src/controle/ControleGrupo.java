package controle;

import dados.RepositorioGrupo;
import modelo.Grupo;
import modelo.Sala;

import java.util.List;

public class ControleGrupo {
    protected RepositorioGrupo repositorioGrupo;

    protected ControleGrupo() {
        repositorioGrupo = new RepositorioGrupo();
    }

    protected boolean adicionar(Grupo grupo) {
        return repositorioGrupo.adicionar(grupo);
    }

    protected boolean remover(Grupo grupo) {
        return repositorioGrupo.remover(grupo);
    }

    protected boolean removerGruposSala(Sala sala) {
        return repositorioGrupo.removerGruposSala(sala);
    }

    protected Grupo buscarGrupo(int codigo){
        return repositorioGrupo.buscarGrupo(codigo);
    }

    public List<Grupo> listarGruposDaSala(Sala sala) {
        return repositorioGrupo.listarGruposDaSala(sala);
    }
}
