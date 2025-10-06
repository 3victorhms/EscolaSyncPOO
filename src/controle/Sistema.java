package controle;

import modelo.*;

public class Sistema {
    protected ControleUsuario controleUsuario;

    private static Sistema instance;

    protected Sistema() {
        controleUsuario = new ControleUsuario();
    }

    public static Sistema getInstance() {
        if (instance == null)
            return new Sistema();
        else
            return instance;
    }

    public boolean login(Usuario usuario) {
        return controleUsuario.login(usuario);
    }

    public boolean cadastrar(Usuario usuario) {
        return controleUsuario.cadastrar(usuario);
    }

    public Usuario buscarUsuario(String username){
        return controleUsuario.buscarUsuario(username);
    }

    public boolean adicionarSala(Sala sala) {
        return false;
    }

    public Data validarData(String data) {
        return Data.
    }

}
