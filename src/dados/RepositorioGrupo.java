package dados;

import modelo.Grupo;
import modelo.Sala;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGrupo {
    protected List <Grupo> grupos;

    public RepositorioGrupo() {
        grupos = new ArrayList<>();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public boolean adicionar(Grupo grupo) {
        return this.getGrupos().add(grupo);
    }

    public boolean remover(Grupo grupo) {
        return this.getGrupos().remove(grupo);
    }

    public Grupo buscarGrupo(int codigo){
        for(Grupo grupo : this.getGrupos()){
            if (grupo.getId() == codigo)
                return grupo;
        }
        return null;
    }

    public boolean removerGruposSala(Sala sala) {
        for (Grupo grupo : this.getGrupos()){
            if(grupo.getSala().getId() == sala.getId())
                this.remover(grupo);
        }
        return true;
    }

    public List<Grupo> listarGruposDaSala(Sala sala) {
        List<Grupo> grupos = new ArrayList<>();
        for (Grupo grupo : this.getGrupos()){
            if(grupo.getSala().getId() == sala.getId())
                grupos.add(grupo);
        }
        return grupos;   
    }
}
