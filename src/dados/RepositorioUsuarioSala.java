package dados;

import modelo.Sala;
import modelo.UsuarioSala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioSala {
    protected List<UsuarioSala> usuarioSalas;

    public RepositorioUsuarioSala() {
        usuarioSalas = new ArrayList<>();
    }

    public List<UsuarioSala> getUsuarioSalas() {
        return usuarioSalas;
    }

    public boolean adicionar(UsuarioSala usuarioSala) {
        return usuarioSalas.add(usuarioSala);
    }

    public boolean remover(UsuarioSala usuarioSala) {
        return usuarioSalas.remove(usuarioSala);
    }

    public boolean removerAlunosSala(Sala sala) {
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()){
            if(usuarioSala.getSala().equals(sala))
                this.remover(usuarioSala);
        }
        return true;
    }
}
