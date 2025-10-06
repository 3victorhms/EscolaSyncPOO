package modelo;

public class UsuarioSala {
    protected Usuario usuario;
    protected Sala sala;

    protected UsuarioSala(Usuario usuario, Sala sala) {
        this.usuario = usuario;
        this.sala = sala;
    }

    public UsuarioSala getInstance(Usuario usuario, Sala sala) {
        if (usuario == null || sala == null) return null;
        return new UsuarioSala(usuario, sala);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Sala getSala() {
        return sala;
    }
}
