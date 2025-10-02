package dados;

import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {
    List<Usuario> usuarios = new ArrayList<>();

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }
}
