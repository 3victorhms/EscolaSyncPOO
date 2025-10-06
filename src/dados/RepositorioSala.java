package dados;

import modelo.Sala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSala {
    List<Sala> salas;

    public RepositorioSala() {
        salas = new ArrayList<>();
    }

    public void adicionar(Sala sala) {
        salas.add(sala);
    }
}
