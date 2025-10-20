package dados;

import modelo.Grupo;
import modelo.Sala;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGrupo {
    protected List<Grupo> grupos;

    public RepositorioGrupo() {
        grupos = new ArrayList<>();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public boolean adicionar(Grupo grupo) {
        return this.getGrupos().add(grupo);
    }

    public boolean excluir(Grupo grupo) {
        return this.getGrupos().remove(grupo);
    }

    public Grupo buscarGrupo(int codigoGrupo) {
        for (Grupo grupo : this.getGrupos()) {
            if (grupo != null)
                if (grupo.getId() == codigoGrupo)
                    return grupo;
        }
        return null;
    }

    public boolean removerGruposSala(Sala sala) {
        for (Grupo grupo : this.getGrupos()) {
            if (grupo != null && grupo.getSala() != null && sala != null)
                if (grupo.getSala().getId() == sala.getId())
                    this.excluir(grupo);
        }
        return true;
    }

    public List<Grupo> listarGruposDaSala(Sala sala) {
        List<Grupo> grupos = new ArrayList<>();
        for (Grupo grupo : this.getGrupos()) {
            if (grupo != null)
                if (grupo.getSala().getId() == sala.getId())
                    grupos.add(grupo);
        }
        return grupos;
    }

    public boolean alterarNome(int codigo, String nome) {
        Grupo grupo = this.buscarGrupo(codigo);
        if (grupo != null) {
            grupo.setNome(nome);
            return true;
        }
        return false;
    }

    public boolean alterarLider(int codigo, Usuario usuario) {
        Grupo grupo = this.buscarGrupo(codigo);
        if (grupo != null) {
            grupo.setLider(usuario);
            return true;
        }
        return false;
    }

    public boolean grupoEhDaSala(Grupo grupo, Sala sala) {
        return grupo.getSala().getId() == sala.getId();
    }
}
