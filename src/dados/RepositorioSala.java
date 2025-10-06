package dados;

import modelo.Sala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSala {
    List<Sala> salas;

    public RepositorioSala() {
        salas = new ArrayList<>();
    }

    public boolean adicionar(Sala sala) {
        return salas.add(sala);
    }

    public boolean remover(Sala sala) {
        return salas.remove(sala);
    }
}
