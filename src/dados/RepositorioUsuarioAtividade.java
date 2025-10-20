package dados;

import modelo.Atividade;
import modelo.Sala;
import modelo.Usuario;
import modelo.UsuarioAtividade;

import java.util.Date;
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
        for (UsuarioAtividade uA : getUsuarioAtividades()) {
            if (usuarioAtividade != null) {
                if (uA.getAtividade().getId() == usuarioAtividade.getAtividade().getId() && uA.getUsuario().getUsername().equals(usuarioAtividade.getUsuario().getUsername()))
                    return usuarioAtividades.remove(uA);
            }
        }
        return false;
    }

    public boolean removerAtividadesDeAlunoDaSala(int idSala, String username) {
        for (UsuarioAtividade usuarioAtividade : this.getUsuarioAtividades()) {
            if (usuarioAtividade.getAtividade().getSala().getId() == idSala && usuarioAtividade.getUsuario().getUsername().equals(username)) {
                this.remover(usuarioAtividade);
            }
        }
        return true;
    }

    public boolean atividadeJaAtribuida(Atividade atividade, Usuario usuario) {
        for (UsuarioAtividade usuarioAtividade : this.getUsuarioAtividades()) {
            if (usuarioAtividade.getAtividade().equals(atividade) && usuarioAtividade.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public boolean alterarStatus(int codigo, String username, String status) {
        for (UsuarioAtividade usuarioAtividade : this.getUsuarioAtividades()) {
            if (usuarioAtividade.getAtividade().getId() == codigo && usuarioAtividade.getUsuario().getUsername().equals(username)) {
                usuarioAtividade.setStatus(status);
                if (status.equals("Concluída"))
                    usuarioAtividade.getAtividade().setDataConclusao(new Date());
                else
                    usuarioAtividade.getAtividade().setDataConclusao(null);
                return true;
            }
        }
        return false;
    }
}
