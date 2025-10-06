package controle;

import modelo.*;

public class Sistema {
    protected ControleUsuario controleUsuario;
    protected ControleSala controleSala;
    protected ControleAtividade controleAtividade;

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
        return controleSala.adicionar(sala);
    }

    public boolean removerSala(Sala sala) {
        return controleSala.remover(sala);
    }

    public boolean entrarSala(int id) {
        return controleSala.entrarSala(id);
    }

    public boolean adicionarAtividade(Atividade atividade) {
        return controleAtividade.adicionar(atividade);
    }

    public boolean removerAtividade(Atividade atividade) {
        return controleAtividade.remover(atividade);
    }

}
