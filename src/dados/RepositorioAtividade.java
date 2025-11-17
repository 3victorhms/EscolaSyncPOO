package dados;

import modelo.Atividade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositorioAtividade {
    List<Atividade> atividades;

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public RepositorioAtividade() {
        atividades = new ArrayList<>();
    }

    public boolean adicionar(Atividade atividade) {
        return atividades.add(atividade);
    }

    public boolean remover(Atividade atividade) {
        return atividades.remove(atividade);
    }

    public Atividade buscarAtividade(int codigo) {
        for (Atividade atividade : this.getAtividades()) {
            if (atividade.getId() == codigo) return atividade;
        }
        return null;
    }

    public Atividade getUltimaAtividade() {
        return this.buscarAtividade(atividades.size());
    }

    public boolean alterarNome(int codigo, String nome) {
        Atividade atividade = this.buscarAtividade(codigo);
        if (atividade != null) {
            atividade.setNome(nome);
            return true;
        }
        return false;
    }

    public boolean alterarDescricao(int codigo, String descricao) {
        Atividade atividade = this.buscarAtividade(codigo);
        if (atividade != null) {
            atividade.setDescricao(descricao);
            return true;
        }
        return false;
    }

    public boolean alterarDataEntrega(int codigo, Date data) {
        Atividade atividade = this.buscarAtividade(codigo);
        if (atividade != null) {
            atividade.setDataEntrega(data);
            return true;
        }
        return false;
    }

    public boolean alterarDataConclusao(int codigo, Date data) {
        Atividade atividade = this.buscarAtividade(codigo);
        if (atividade != null) {
            atividade.setDataConclusao(data);
            return true;
        }
        return false;
    }

    public boolean alterarMateria(int codigo, String materia) {
        Atividade atividade = this.buscarAtividade(codigo);
        if (atividade != null) {
            atividade.setMateria(materia);
            return true;
        }
        return false;
    }

    public boolean alterarValor(int codigo, double valor) {
        Atividade atividade = this.buscarAtividade(codigo);
        if (atividade != null) {
            atividade.setValor(valor);
            return true;
        }
        return false;
    }
}
