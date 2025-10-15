package modelo;

public class Grupo {
    protected int id;
    protected String nome;
    protected Sala sala;
    protected Usuario lider;

    protected static int proxId = 1;

    protected Grupo(String nome, Sala sala, Usuario lider) {
        this.id = proxId++;
        this.nome = nome;
        this.sala = sala;
        this.lider = lider;
    }

    public static Grupo getInstance(String nome, Sala sala, Usuario lider) {
        if (nome.isEmpty() || sala == null || lider == null) return null;
        return new Grupo(nome, sala, lider);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Sala getSala() {
        return sala;
    }

    public Usuario getLider() {
        return lider;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLider(Usuario lider) {
        this.lider = lider;
    }

    @Override
    public String toString() {
        return "Grupo {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sala=" + sala +
                ", líder=" + lider +
                '}';
    }
}
