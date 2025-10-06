package modelo;

public class Usuario {
    protected int id;
    protected String username;
    protected String password;

    protected  static int proxId = 1;

    protected Usuario(String username, String password) {
        this.id = proxId++;
        this.username = username;
        this.password = password;
    }

    public static Usuario getInstance(String username, String password) {
        if (username == null || password == null) return null;
        return new Usuario(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
