package dados;

import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {
    List<Usuario> usuarios;

    public RepositorioUsuario() {
        usuarios = new ArrayList<>();
    }

    public boolean cadastrar(Usuario usuario) {
        if (verificarUsername(usuario.getUsername())) {
            usuarios.add(usuario);
            return true;
        }
        return false;
    }

    public boolean login(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(usuario.getUsername()) && u.getPassword().equalsIgnoreCase(usuario.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public Usuario buscarUsuario(String username){
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }
}
