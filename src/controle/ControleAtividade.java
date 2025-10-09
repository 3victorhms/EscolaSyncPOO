package controle;

import dados.RepositorioAtividade;
import modelo.Atividade;

public class ControleAtividade {
    protected RepositorioAtividade repositorioAtividade;

    protected ControleAtividade() {
        repositorioAtividade = new RepositorioAtividade();
    }

    public boolean adicionar(Atividade atividade) {
        return repositorioAtividade.adicionar(atividade);
    }

    public boolean excluir(Atividade atividade) {
        return repositorioAtividade.remover(atividade);
    }

    public Atividade buscarAtividade(int codigo){
        return repositorioAtividade.buscarAtividade(codigo);
    }
}
