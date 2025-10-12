package controle;

import modelo.*;

public class Sistema {
    protected ControleUsuario controleUsuario;
    protected ControleSala controleSala;
    protected ControleAtividade controleAtividade;
    protected ControleUsuarioSala controleUsuarioSala;
    protected ControleUsuarioAtividade controleUsuarioAtividade;
    protected ControleUsuarioGrupo controleUsuarioGrupo;
    protected ControleSalaAtividade controleSalaAtividade;
    protected ControleGrupo controleGrupo;

    private static Sistema instance;

    protected Sistema() {
        controleUsuario = new ControleUsuario();
        controleSala = new ControleSala();
        controleAtividade = new ControleAtividade();
        controleUsuarioSala = new ControleUsuarioSala();
        controleUsuarioAtividade = new ControleUsuarioAtividade();
        controleUsuarioGrupo = new ControleUsuarioGrupo();
        controleGrupo = new ControleGrupo();
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

    public boolean excluirSala(int codigo) {
        return controleSala.excluir(controleSala.buscarSala(codigo));
    }

    public boolean removerAlunosSala(int codigo) {
        return controleUsuarioSala.removerAlunosSala(controleSala.buscarSala(codigo));
    }

    public boolean removerAtividadesSala(int codigo) {
        return controleSalaAtividade.removerAtividadesSala(controleSala.buscarSala(codigo));
    }

    public boolean removerGruposSala(int codigo) {
        return controleGrupo.removerGruposSala(controleSala.buscarSala(codigo));
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


    public boolean entrarGrupo(Grupo grupo) {
        return controleUsuarioGrupo.entrarGrupo(this.controleUsuario.getUsuarioAtual(), grupo);
    }

    public Grupo buscarGrupo(int codigo) {
        return controleGrupo.buscarGrupo(codigo);
    }
}
