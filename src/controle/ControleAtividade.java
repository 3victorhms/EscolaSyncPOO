package controle;

import dados.RepositorioAtividade;
import modelo.Atividade;
import modelo.Sala;

import java.util.Date;

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

    public boolean alterarNome(int codigo, String nome){
        return repositorioAtividade.alterarNome(codigo, nome);
    }

    public boolean alterarDescricao(int codigo, String descricao){
        return repositorioAtividade.alterarDescricao(codigo, descricao);
    }

    public boolean alterarDataEntrega(int codigo, Date data){
        return repositorioAtividade.alterarDataEntrega(codigo, data);
    }

    public boolean alterarDataConclusao(int codigo, Date data){
        return repositorioAtividade.alterarDataConclusao(codigo, data);
    }

    public boolean alterarMateria(int codigo, String materia){
        return repositorioAtividade.alterarMateria(codigo, materia);
    }

    public boolean alterarValor(int codigo, double valor){
        return repositorioAtividade.alterarValor(codigo, valor);
    }
}
