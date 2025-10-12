package controle;

import dados.RepositorioUsuarioAtividade;
import modelo.Atividade;
import modelo.Usuario;
import modelo.UsuarioAtividade;

public class ControleUsuarioAtividade {
    protected RepositorioUsuarioAtividade repositorioUsuarioAtividade;

    protected ControleUsuarioAtividade() {
        repositorioUsuarioAtividade = new RepositorioUsuarioAtividade();
    }

    public boolean adicionar(UsuarioAtividade usuarioAtividade) {
        return repositorioUsuarioAtividade.adcionar(usuarioAtividade);
    }

    public boolean remover(UsuarioAtividade usuarioAtividade) {
        return repositorioUsuarioAtividade.remover(usuarioAtividade);
    }

    public boolean removerAtividadesDeAlunoDaSala(int idSala, String username) {
        return repositorioUsuarioAtividade.removerAtividadesDeAlunoDaSala(idSala, username);
    }
}
