package dados;

import modelo.Atividade;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAtividade {
    List<Atividade> atividades;

    public RepositorioAtividade() {
        atividades = new ArrayList<>();
    }

    public void adicionar(Atividade atividade) {
        atividades.add(atividade);
    }
}
