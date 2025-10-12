package dados;

import modelo.Sala;
import modelo.UsuarioAtividade;

import java.util.List;
import java.util.ArrayList;

public class RepositorioUsuarioAtividade {
    protected List<UsuarioAtividade> usuarioAtividades;

    public RepositorioUsuarioAtividade() {
        usuarioAtividades = new ArrayList<>();
    }

    public List<UsuarioAtividade> getUsuarioAtividades() {
        return usuarioAtividades;
    }

    public boolean adcionar(UsuarioAtividade usuarioAtividade) {
        return usuarioAtividades.add(usuarioAtividade);
    }

    public boolean remover(UsuarioAtividade usuarioAtividade) {
        return usuarioAtividades.remove(usuarioAtividade);
    }

    public boolean removerAtividadesDeAlunoDaSala(int idSala, String username) {
        for (UsuarioAtividade usuarioAtividade : this.getUsuarioAtividades()){
            if (usuarioAtividade.getAtividade().getSala().getId() == idSala && usuarioAtividade.getUsuario().getUsername().equals(username)) {
                this.remover(usuarioAtividade);
            }
        }
        return true;
    }
}
