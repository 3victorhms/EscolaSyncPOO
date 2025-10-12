package modelo;

public class UsuarioAtividade {
    protected Usuario usuario;
    protected Atividade atividade;
    protected String status;

    protected UsuarioAtividade(Usuario usuario, Atividade atividade) {
        this.usuario = usuario;
        this.atividade = atividade;
        this.status = "Pendente";
    }

    public static UsuarioAtividade getInstance(Usuario usuario, Atividade atividade) {
        if (usuario == null || atividade == null) return null;
        return new UsuarioAtividade(usuario, atividade);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
