package dados;

import modelo.Sala;
import modelo.Usuario;
import modelo.UsuarioSala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioSala {
    protected List<UsuarioSala> usuarioSalas;

    public RepositorioUsuarioSala() {
        usuarioSalas = new ArrayList<>();
    }

    public List<UsuarioSala> getUsuarioSalas() {
        return usuarioSalas;
    }

    public boolean adicionar(UsuarioSala usuarioSala) {
        if (usuarioSala != null)
            if (!usuarioEstaNaSala(usuarioSala.getSala().getId(), usuarioSala.getUsuario().getUsername()))
                return usuarioSalas.add(usuarioSala);
        return false;
    }

    public boolean usuarioEstaNaSala(int idSala, String username) {
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()) {
            if (usuarioSala.getUsuario().getUsername().equals(username) && usuarioSala.getSala().getId() == idSala)
                return true;
        }
        return false;
    }

    public boolean remover(UsuarioSala usuarioSala) {
        return usuarioSalas.remove(usuarioSala);
    }

    public boolean removerAlunosSala(Sala sala) {
        List<UsuarioSala> aRemover = new ArrayList<>();
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()) {
            if (usuarioSala.getSala().equals(sala))
                aRemover.add(usuarioSala);
        }
        for (UsuarioSala usuarioSala : aRemover) {
            this.remover(usuarioSala);
        }
        return true;
    }

    public List<Sala> listarSalasDoUsuario(Usuario usuarioAtual) {
        List<Sala> salas = new ArrayList<>();
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()) {
            if (usuarioSala != null && usuarioSala.getUsuario().equals(usuarioAtual))
                salas.add(usuarioSala.getSala());
        }
        return salas;
    }

    public List<Usuario> listarParticipantesSala(Sala sala) {
        List<Usuario> participantes = new ArrayList<>();
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()) {
            if (usuarioSala.getSala().equals(sala))
                participantes.add(usuarioSala.getUsuario());
        }
        return participantes;
    }
}
