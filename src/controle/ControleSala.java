package controle;

import dados.RepositorioSala;
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

    protected boolean excluir(Sala sala) {
        return repositorioSala.excluir(sala);
    }

    public Sala buscarSala(int idSala) {
        return repositorioSala.buscarSala(idSala);
    }

    public boolean atualizarNome(int id, String novoNome) {
        return repositorioSala.atualizarNome(id, novoNome);
    }

    public boolean atualizarDescricao(int id, String novaDescricao) {
        return repositorioSala.atualizarDescricao(id, novaDescricao);
    }

    public boolean atualizarLider(int id, Usuario usuario) {
        return repositorioSala.atualizarLider(id, usuario);
    }

    protected boolean entrarSala(int id) {
        Sala sala = repositorioSala.buscar(id);
        return sala != null;
    }

    protected Sala buscar(int id) {
        return repositorioSala.buscar(id);
    }
}
