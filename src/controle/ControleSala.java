package controle;

import dados.RepositorioSala;
import modelo.Sala;

public class ControleSala {
    protected RepositorioSala repositorioSala;

    protected ControleSala() {
        repositorioSala = new RepositorioSala();
    }

    protected boolean adicionar(Sala sala) {
        return repositorioSala.adicionar(sala);
    }

    protected boolean remover(Sala sala) {
        return repositorioSala.remover(sala);
    }

    protected boolean entrarSala(int id) {
        Sala sala = repositorioSala.buscar(id);
        return sala != null;
    }

    protected Sala buscar(int id) {
        return repositorioSala.buscar(id);
    }
}
