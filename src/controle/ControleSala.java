package controle;

import dados.RepositorioSala;
import excecoes.EmptyException;
import modelo.Sala;
import modelo.Usuario;

public class ControleSala {
    protected RepositorioSala repositorioSala;

    protected ControleSala() {
        repositorioSala = new RepositorioSala();
    }

    protected boolean adicionar(Sala sala) {
        return repositorioSala.adicionar(sala);
    }

    // Novo método com validações
    public boolean adicionarComValidacao(Sala sala) throws EmptyException {
        if (sala == null)
            throw new EmptyException("Sala não pode ser nula.");
        if (sala.getNome() == null || sala.getNome().isBlank())
            throw new EmptyException("O nome da sala não pode ser vazio.");
        if (sala.getDescricao() == null || sala.getDescricao().isBlank())
            throw new EmptyException("A descrição da sala não pode ser vazia.");
        if (sala.getLider() == null)
            throw new EmptyException("A sala deve ter um líder.");
        
        return repositorioSala.adicionar(sala);
    }

    protected boolean excluir(Sala sala) {
        return repositorioSala.excluir(sala);
    }

    public Sala buscarSala(int idSala) {
        return repositorioSala.buscarSala(idSala);
    }

    public boolean atualizarNome(int id, String novoNome) {
        return repositorioSala.atualizarNome(id, novoNome);
    }

    public boolean atualizarNomeComValidacao(int id, String novoNome) throws EmptyException {
        if (novoNome == null || novoNome.isBlank())
            throw new EmptyException("O nome da sala não pode ser vazio.");
        return repositorioSala.atualizarNome(id, novoNome);
    }

    public boolean atualizarDescricao(int id, String novaDescricao) {
        return repositorioSala.atualizarDescricao(id, novaDescricao);
    }

    public boolean atualizarDescricaoComValidacao(int id, String novaDescricao) throws EmptyException {
        if (novaDescricao == null || novaDescricao.isBlank())
            throw new EmptyException("A descrição da sala não pode ser vazia.");
        return repositorioSala.atualizarDescricao(id, novaDescricao);
    }

    public boolean atualizarLider(int id, Usuario usuario) {
        return repositorioSala.atualizarLider(id, usuario);
    }
}
