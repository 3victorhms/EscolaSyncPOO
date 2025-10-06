package dados;

import modelo.UsuarioSala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioSala {
    protected List<UsuarioSala> usuarioSalas;

    public RepositorioUsuarioSala() {
        usuarioSalas = new ArrayList<>();
    }

    public boolean adicionar(UsuarioSala usuarioSala) {
        return usuarioSalas.add(usuarioSala);
    }

    public boolean remover(UsuarioSala usuarioSala) {
        return usuarioSalas.remove(usuarioSala);
    }
}
