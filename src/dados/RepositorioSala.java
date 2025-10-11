package dados;

import modelo.Sala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioSala {
    List<Sala> salas;

    public RepositorioSala() {
        salas = new ArrayList<>();
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public boolean adicionar(Sala sala) {
        return salas.add(sala);
    }

    public boolean remover(Sala sala) {
        return salas.remove(sala);
    }

    public Sala buscarSala(int idSala) {
        for(Sala sala : this.getSalas()){
            if(sala.getId() == idSala) return sala;
        }
        return null;
    }
}
