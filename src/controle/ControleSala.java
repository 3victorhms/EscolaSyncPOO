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

    public Sala buscarSala(int idSala) {
        return repositorioSala.buscarSala(idSala);
    }
}
