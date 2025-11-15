package controle;

import dados.RepositorioAtividade;
import excecoes.DataInvalidaException;
import excecoes.EmptyException;
import excecoes.TipoAtividadeInvalidoException;
import excecoes.ValorInvalidoException;
import modelo.Atividade;
import modelo.Prova;
import modelo.Sala;
import modelo.Tarefa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControleAtividade {
    protected RepositorioAtividade repositorioAtividade;

    protected ControleAtividade() {
        repositorioAtividade = new RepositorioAtividade();
    }

    public boolean adicionar(int tipo, String nome, String descricao, String dt, String materia, double valor, Sala sala, String extra) throws Exception {
        if (tipo != 1 && tipo != 2)
            throw new TipoAtividadeInvalidoException("Tipo de atividade inválido.");

        if (nome == null || nome.isBlank())
            throw new EmptyException("O nome não pode ser vazio.");

        if (descricao == null || descricao.isBlank())
            throw new EmptyException("A descrição não pode ser vazia.");

        Date data;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dt);
        } catch (Exception e) {
            throw new Exception("Data inválida. Use o formato dd/MM/yyyy.");
        }

        if (materia == null || materia.isBlank())
            throw new EmptyException("A matéria não pode ser vazia.");

        if (valor <= 0)
            throw new Exception("O valor deve ser maior que zero.");

        if (sala == null)
            throw new Exception("Sala inexistente.");

        Atividade atividade;

        if (tipo == 1) {
            atividade = Tarefa.getInstance(nome, descricao, data, null, materia, valor, sala, extra);
        } else {
            boolean consulta = extra.equalsIgnoreCase("S");
            atividade = Prova.getInstance(nome, descricao, data, null, materia, valor, sala, consulta);
        }
        return repositorioAtividade.adicionar(atividade);
    }

    public boolean excluir(Atividade atividade) {
        return repositorioAtividade.remover(atividade);
    }

    public Atividade buscarAtividade(int codigo) {
        return repositorioAtividade.buscarAtividade(codigo);
    }

    public Atividade buscarUltimaAtividade() {
        return repositorioAtividade.getUltimaAtividade();
    }

    public boolean alterarNome(int codigo, String nome) throws EmptyException {
        if (nome == null || nome.isBlank())
            throw new EmptyException("O nome não pode ser vazio.");
        return repositorioAtividade.alterarNome(codigo, nome);
    }

    public boolean alterarDescricao(int codigo, String descricao) throws EmptyException {
        if (descricao == null || descricao.isBlank())
            throw new EmptyException("O nome não pode ser vazio.");
        return repositorioAtividade.alterarDescricao(codigo, descricao);
    }

    public boolean alterarDataEntrega(int codigo, String dt) throws DataInvalidaException {
        if (dt == null || dt.isBlank())
            throw new DataInvalidaException("A data não pode ser vazia.");

        Date data;

        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dt);
        } catch (Exception e) {
            throw new DataInvalidaException("Data inválida. Use o formato dd/MM/yyyy.");
        }
        return repositorioAtividade.alterarDataEntrega(codigo, data);
    }

    public boolean alterarMateria(int codigo, String materia) throws EmptyException {
        if (materia == null || materia.isBlank())
            throw new EmptyException("A matéria não pode ser vazia.");
        return repositorioAtividade.alterarMateria(codigo, materia);
    }

    public boolean alterarValor(int codigo, double valor) throws ValorInvalidoException {
        if (valor <= 0)
            throw new ValorInvalidoException("O valor deve ser maior que zero.");
        return repositorioAtividade.alterarValor(codigo, valor);
    }
}
