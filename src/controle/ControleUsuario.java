package controle;

import dados.RepositorioUsuario;
import excecoes.EmptyException;
import modelo.Usuario;

public class ControleUsuario {
    protected RepositorioUsuario repositorioUsuario;

    protected ControleUsuario() {
        repositorioUsuario = new RepositorioUsuario();
    }

    public boolean login(Usuario usuario) {
        return repositorioUsuario.login(usuario);
    }

    public boolean loginComValidacao(Usuario usuario) throws EmptyException {
        if (usuario == null)
            throw new EmptyException("Usuário não pode ser nulo.");
        if (usuario.getUsername() == null || usuario.getUsername().isBlank())
            throw new EmptyException("O username não pode ser vazio.");
        if (usuario.getPassword() == null || usuario.getPassword().isBlank())
            throw new EmptyException("A senha não pode ser vazia.");
        
        return repositorioUsuario.login(usuario);
    }

    public boolean cadastrar(Usuario usuario) {
        return repositorioUsuario.cadastrar(usuario);
    }

    public boolean cadastrarComValidacao(Usuario usuario) throws EmptyException {
        if (usuario == null)
            throw new EmptyException("Usuário não pode ser nulo.");
        if (usuario.getUsername() == null || usuario.getUsername().isBlank())
            throw new EmptyException("O username não pode ser vazio.");
        if (usuario.getPassword() == null || usuario.getPassword().isBlank())
            throw new EmptyException("A senha não pode ser vazia.");
        
        return repositorioUsuario.cadastrar(usuario);
    }

    public Usuario buscarUsuario(String username) {
        return repositorioUsuario.buscarUsuario(username);
    }

    public Usuario getUsuarioAtual() {
        return repositorioUsuario.getUsuarioAtual();
    }

    public boolean logout() {
        return repositorioUsuario.logout();
    }
}