package dados;

import modelo.Atividade;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAtividade {
    List<Atividade> atividades;

    public RepositorioAtividade() {
        atividades = new ArrayList<>();
    }

    public boolean adicionar(Atividade atividade) {
        return atividades.add(atividade);
    }

    public boolean remover(Atividade atividade) {
        return atividades.remove(atividade);
    }

    public Atividade buscarAtividade(int codigo){
        for(Atividade atividade : atividades){
            if(atividade.getCodigo() == codigo) return atividade;
        }
        return null;
    }
}
