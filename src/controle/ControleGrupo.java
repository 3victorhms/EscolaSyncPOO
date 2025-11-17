package controle;

import dados.RepositorioGrupo;
import excecoes.EmptyException;
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

    // Novo método com validações
    public boolean adicionarComValidacao(Grupo grupo) throws EmptyException {
        if (grupo == null)
            throw new EmptyException("Grupo não pode ser nulo.");
        if (grupo.getNome() == null || grupo.getNome().isBlank())
            throw new EmptyException("O nome do grupo não pode ser vazio.");
        if (grupo.getSala() == null)
            throw new EmptyException("O grupo deve estar associado a uma sala.");
        if (grupo.getLider() == null)
            throw new EmptyException("O grupo deve ter um líder.");
        
        return repositorioGrupo.adicionar(grupo);
    }

    public boolean excluir(Grupo grupo) {
        return repositorioGrupo.excluir(grupo);
    }

    protected boolean removerGruposSala(Sala sala) {
        return repositorioGrupo.removerGruposSala(sala);
    }

    protected Grupo buscarGrupo(int codigoGrupo) {
        return repositorioGrupo.buscarGrupo(codigoGrupo);
    }

    public List<Grupo> listarGruposDaSala(Sala sala) {
        return repositorioGrupo.listarGruposDaSala(sala);
    }


    public boolean alterarNome(int codigo, String nome) {
        return repositorioGrupo.alterarNome(codigo, nome);
    }

    public boolean alterarNomeComValidacao(int codigo, String nome) throws EmptyException {
        if (nome == null || nome.isBlank())
            throw new EmptyException("O nome do grupo não pode ser vazio.");
        return repositorioGrupo.alterarNome(codigo, nome);
    }

    public boolean alterarLider(int codigo, Usuario usuario) {
        return repositorioGrupo.alterarLider(codigo, usuario);
    }

    public boolean grupoEhDaSala(Grupo grupo, Sala sala) {
        return repositorioGrupo.grupoEhDaSala(grupo, sala);
    }
}
