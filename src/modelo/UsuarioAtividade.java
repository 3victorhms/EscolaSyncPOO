package modelo;

public class UsuarioAtividade {
    protected Usuario usuario;
    protected Atividade atividade;

    protected UsuarioAtividade(Usuario usuario, Atividade atividade) {
        this.usuario = usuario;
        this.atividade = atividade;
    }

    public UsuarioAtividade getInstance(Usuario usuario, Atividade atividade) {
        if (usuario == null || atividade == null) return null;
        return new UsuarioAtividade(usuario, atividade);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Atividade getAtividade() {
        return atividade;
    }
}
