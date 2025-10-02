package modelo;

public class Usuario {
    protected String username;
    protected String email;
    protected String password;

    protected Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static Usuario getInstance(String username, String email, String password) {
        return new Usuario(username, email, password);
    }

}
