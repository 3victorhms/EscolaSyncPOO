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
        return usuarioSalas.add(usuarioSala);
    }

    public boolean remover(UsuarioSala usuarioSala) {
        return usuarioSalas.remove(usuarioSala);
    }

    public boolean removerAlunosSala(Sala sala) {
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()){
            if(usuarioSala.getSala().equals(sala))
                this.remover(usuarioSala);
        }
        return true;
    }

    public List<Sala> listarSalasDoUsuario(Usuario usuarioAtual) {
        List<Sala> salas = new ArrayList<>();
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()){
            if(usuarioSala.getUsuario().equals(usuarioAtual))
                salas.add(usuarioSala.getSala());
        }
        return salas;
    }

    public List<Usuario> listarParticipantesSala(Sala sala) {
        List<Usuario> participantes = new ArrayList<>();
        for (UsuarioSala usuarioSala : this.getUsuarioSalas()){
            if(usuarioSala.getSala().equals(sala))
                participantes.add(usuarioSala.getUsuario());
        }
        return participantes;
    }
}
