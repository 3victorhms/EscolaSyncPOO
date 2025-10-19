package dados;

import modelo.Sala;
import modelo.Usuario;

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
        if (sala == null) return false;
        return salas.add(sala);
    }

    public boolean excluir(Sala sala) {
        if (sala == null) return false;
        return salas.remove(sala);
    }

    public Sala buscarSala(int idSala) {
        for(Sala sala : this.getSalas()){
            if(sala.getId() == idSala) return sala;
        }
        return null;
    }

    public boolean atualizarNome(int id, String novoNome) {
        for (Sala sala : this.getSalas()) {
            if (sala.getId() == id) {
                sala.setNome(novoNome);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarDescricao(int id, String novaDescricao) {
        for (Sala sala : this.getSalas()) {
            if (sala.getId() == id) {
                sala.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }

    public boolean atualizarLider(int id, Usuario usuario) {
        for (Sala sala : this.getSalas()) {
            if (sala.getId() == id) {
                sala.setLider(usuario);
                return true;
            }
        }
        return false;
    }
}
