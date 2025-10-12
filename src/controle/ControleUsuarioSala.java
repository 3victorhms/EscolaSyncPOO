package controle;

import dados.RepositorioUsuarioSala;
import modelo.Sala;
import modelo.Usuario;
import modelo.UsuarioSala;

import java.util.List;

public class ControleUsuarioSala {
    protected RepositorioUsuarioSala repositorioUsuarioSala;

    protected ControleUsuarioSala() {
        repositorioUsuarioSala = new RepositorioUsuarioSala();
    }

    public boolean entrarSala(UsuarioSala usuarioSala) {
        return repositorioUsuarioSala.adicionar(usuarioSala);
    }

    public boolean sairSala(UsuarioSala usuarioSala) {
        return repositorioUsuarioSala.remover(usuarioSala);
    }

    public boolean removerAlunosSala(Sala sala) {
        return repositorioUsuarioSala.removerAlunosSala(sala);
    }

    public List<Sala> listarSalasDoUsuario(Usuario usuarioAtual) {
        return repositorioUsuarioSala.listarSalasDoUsuario(usuarioAtual);
    }

    public List<Usuario> listarParticipantesSala(Sala sala) {
        return repositorioUsuarioSala.listarParticipantesSala(sala);
    }
}
