package controle;

import dados.RepositorioGrupo;
import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.util.List;

public class ControleGrupo {
    protected RepositorioGrupo repositorioGrupo;

    protected ControleGrupo() {
        repositorioGrupo = new RepositorioGrupo();
    }

    protected boolean adicionar(Grupo grupo) {
        return repositorioGrupo.adicionar(grupo);
    }

    public boolean excluir(Grupo grupo) {
        return repositorioGrupo.excluir(grupo);
    }

    protected boolean removerGruposSala(Sala sala) {
        return repositorioGrupo.removerGruposSala(sala);
    }

    protected Grupo buscarGrupo(int codigoGrupo){
        return repositorioGrupo.buscarGrupo(codigoGrupo);
    }

    public List<Grupo> listarGruposDaSala(Sala sala) {
        return repositorioGrupo.listarGruposDaSala(sala);
    }


    public boolean alterarNome(int codigo, String nome) {
        return repositorioGrupo.alterarNome(codigo, nome);
    }

    public boolean alterarLider(int codigo, Usuario usuario) {
        return repositorioGrupo.alterarLider(codigo, usuario);
    }
}
