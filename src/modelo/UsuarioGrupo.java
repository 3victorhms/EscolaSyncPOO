package modelo;

public class UsuarioGrupo {
    protected Usuario usuario;
    protected Grupo grupo;

    protected UsuarioGrupo(Usuario usuario, Grupo grupo) {
        this.usuario = usuario;
        this.grupo = grupo;
    }

    public static UsuarioGrupo getInstance(Usuario usuario, Grupo grupo) {
        if (usuario == null || grupo == null) return null;
        return new UsuarioGrupo(usuario, grupo);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }
}
