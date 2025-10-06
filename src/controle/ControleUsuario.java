package controle;

import dados.RepositorioUsuario;
import modelo.Usuario;

public class ControleUsuario {
    protected RepositorioUsuario repositorioUsuario;

    protected ControleUsuario() {
        repositorioUsuario = new RepositorioUsuario();
    }

    public boolean login(Usuario usuario) {
        return repositorioUsuario.login(usuario);
    }

    public boolean cadastrar(Usuario usuario) {
        return repositorioUsuario.cadastrar(usuario);
    }

    public Usuario buscarUsuario(String username){
        return repositorioUsuario.buscarUsuario(username);
    }
}
