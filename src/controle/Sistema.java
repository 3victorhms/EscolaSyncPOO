package controle;

import modelo.*;

public class Sistema {
    protected ControleUsuario controleUsuario;
    protected ControleSala controleSala;
    protected ControleAtividade controleAtividade;
    protected ControleUsuarioSala controleUsuarioSala;

    private static Sistema instance;

    protected Sistema() {
        controleUsuario = new ControleUsuario();
    }

    public static Sistema getInstance() {
        if (instance == null)
            return new Sistema();
        else
            return instance;
    }

    public boolean login(Usuario usuario) {
        return controleUsuario.login(usuario);
    }

    public boolean cadastrar(Usuario usuario) {
        return controleUsuario.cadastrar(usuario);
    }

    public Usuario buscarUsuario(String username){
        return controleUsuario.buscarUsuario(username);
    }

    public boolean adicionarSala(Sala sala) {
        return controleSala.adicionar(sala);
    }

    public boolean removerSala(Sala sala) {
        return controleSala.remover(sala);
    }

    public boolean entrarSala(int idSala) {
        return controleUsuarioSala.entrarSala(UsuarioSala.getInstance(controleUsuario.getUsuarioAtual(), controleSala.buscarSala(idSala)));
    }

    public boolean adicionarAtividade(Atividade atividade) {
        return controleAtividade.adicionar(atividade);
    }

    public boolean excluirAtividade(int codigo) {
        return controleAtividade.excluir(this.buscarAtividade(codigo));
    }

    public Atividade buscarAtividade(int codigo){
        return controleAtividade.buscarAtividade(codigo);
    }

    public boolean alterarNomeAtividade(int codigo, String nome) {
        return controleAtividade.alterarNome(codigo, nome);
    }

    public boolean alterarDescricaoAtividade(int codigo, String descricao) {
        return controleAtividade.alterarDescricao(codigo, descricao);
    }

    public boolean alterarDataEntregaAtividade(int codigo, java.util.Date data) {
        return controleAtividade.alterarDataEntrega(codigo, data);
    }

    public boolean alterarDataConclusaoAtividade(int codigo, java.util.Date data) {
        return controleAtividade.alterarDataConclusao(codigo, data);
    }

    public boolean alterarMateriaAtividade(int codigo, String materia) {
        return controleAtividade.alterarMateria(codigo, materia);
    }

    public boolean alterarValorAtividade(int codigo, double valor) {
        return controleAtividade.alterarValor(codigo, valor);
    }


}
