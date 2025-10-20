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

    public boolean atividadeJaAtribuida(Atividade atividade, Usuario usuario) {
        return repositorioUsuarioAtividade.atividadeJaAtribuida(atividade, usuario);
    }

    public boolean alterarStatus(int codigo, String username, String status) {
        return repositorioUsuarioAtividade.alterarStatus(codigo, username, status);
    }
}
