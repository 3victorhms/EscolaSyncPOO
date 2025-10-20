package controle;

import modelo.*;

import java.util.Date;
import java.util.List;

public class Sistema {

    // ============================================
    // Atributos
    // ============================================
    protected ControleUsuario controleUsuario;
    protected ControleSala controleSala;
    protected ControleAtividade controleAtividade;
    protected ControleUsuarioSala controleUsuarioSala;
    protected ControleUsuarioAtividade controleUsuarioAtividade;
    protected ControleUsuarioGrupo controleUsuarioGrupo;
    protected ControleSalaAtividade controleSalaAtividade;
    protected ControleGrupo controleGrupo;

    private static Sistema instance;

    // ============================================
    // Construtor e Singleton
    // ============================================
    protected Sistema() {
        controleUsuario = new ControleUsuario();
        controleSala = new ControleSala();
        controleAtividade = new ControleAtividade();
        controleUsuarioSala = new ControleUsuarioSala();
        controleUsuarioAtividade = new ControleUsuarioAtividade();
        controleUsuarioGrupo = new ControleUsuarioGrupo();
        controleGrupo = new ControleGrupo();
        controleSalaAtividade = new ControleSalaAtividade();
        iniciar();
    }

    public static Sistema getInstance() {
        if (instance == null)
            instance = new Sistema();
        return instance;
    }

    public void iniciar() {
        controleUsuario.cadastrar(Usuario.getInstance("v", "1"));
        controleUsuario.cadastrar(Usuario.getInstance("m", "2"));

        controleSala.adicionar(Sala.getInstance("DS1.24", "DS1.24",new Date("06/02/2024"), this.buscarUsuario("v")));
        controleSala.adicionar(Sala.getInstance("DS2.25", "DS2.25",new Date("06/02/2025"), this.buscarUsuario("m")));
        controleUsuarioSala.entrarSala(UsuarioSala.getInstance(this.buscarUsuario("v"), controleSala.buscarSala(1)));
        controleUsuarioSala.entrarSala(UsuarioSala.getInstance(this.buscarUsuario("m"), controleSala.buscarSala(2)));

        controleGrupo.adicionar(Grupo.getInstance("Trabalho FP VH e M", controleSala.buscarSala(1), this.buscarUsuario("v")));
        controleGrupo.adicionar(Grupo.getInstance("Trabalho POO VH e M", controleSala.buscarSala(2), this.buscarUsuario("m")));
        controleUsuarioGrupo.entrarGrupo(this.buscarUsuario("v"), controleGrupo.buscarGrupo(1));
        controleUsuarioGrupo.entrarGrupo(this.buscarUsuario("m"), controleGrupo.buscarGrupo(2));

        controleAtividade.adicionar(Atividade.getInstance("Trabalho FP", "Trabalho FP", new Date("20/10/2024"), null, "FP", 10.0, controleSala.buscarSala(1)));
        controleAtividade.adicionar(Atividade.getInstance("Trabalho POO", "Trabalho POO", new Date("20/10/2025"), null, "POO", 10.0, controleSala.buscarSala(2)));
        controleSalaAtividade.adicionar(SalaAtividade.getInstance(controleSala.buscarSala(1), controleAtividade.buscarAtividade(1)));
        controleSalaAtividade.adicionar(SalaAtividade.getInstance(controleSala.buscarSala(2), controleAtividade.buscarAtividade(2)));
    }

    // ============================================
    // Usuário
    // ============================================
    public boolean login(Usuario usuario) {
        return controleUsuario.login(usuario);
    }

    public boolean cadastrar(Usuario usuario) {
        return controleUsuario.cadastrar(usuario);
    }

    public boolean logout() {
        return controleUsuario.logout();
    }

    public Usuario getUsuarioAtual() {
        return controleUsuario.getUsuarioAtual();
    }

    public Usuario buscarUsuario(String username) {
        return controleUsuario.buscarUsuario(username);
    }

    public boolean usuarioExiste(String username) {
        return controleUsuario.buscarUsuario(username) != null;
    }

    // ============================================
    // Sala
    // ============================================
    public boolean adicionarSala(Sala sala) {
        return controleSala.adicionar(sala);
    }

    public boolean excluirSala(int codigo) {
        return controleSala.excluir(controleSala.buscarSala(codigo));
    }

    public Sala buscarSala(int codigo) {
        return controleSala.buscarSala(codigo);
    }

    public boolean salaExiste(int codigo) {
        return controleSala.buscarSala(codigo) != null;
    }

    public boolean entrarSala(int idSala) {
        return controleUsuarioSala.entrarSala(
                UsuarioSala.getInstance(controleUsuario.getUsuarioAtual(), controleSala.buscarSala(idSala))
        );
    }

    public boolean sairSala(int idSala) {
        return controleUsuarioSala.sairSala(
                UsuarioSala.getInstance(controleUsuario.getUsuarioAtual(), controleSala.buscarSala(idSala))
        );
    }

    public List<Sala> listarSalasDoUsuario() {
        return controleUsuarioSala.listarSalasDoUsuario(this.getUsuarioAtual());
    }

    public List<Usuario> listarParticipantesSala(int codigoSala) {
        return controleUsuarioSala.listarParticipantesSala(controleSala.buscarSala(codigoSala));
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

    public boolean atualizarNomeSala(int id, String novoNome) {
        return controleSala.atualizarNome(id, novoNome);
    }

    public boolean atualizarDescricaoSala(int id, String novaDescricao) {
        return controleSala.atualizarDescricao(id, novaDescricao);
    }

    public boolean atualizarLiderSala(int id, String novoLider) {
        return controleSala.atualizarLider(id, this.buscarUsuario(novoLider));
    }

    public boolean usuarioEstaNaSala(int id, String username) {
        return controleUsuarioSala.usuarioEstaNaSala(id, username);
    }

    // ============================================
    // Atividade
    // ============================================
    public boolean adicionarAtividade(Atividade atividade, int idSala) {
        if (controleAtividade.adicionar(atividade)) {
            return controleSalaAtividade.adicionar(SalaAtividade.getInstance(controleSala.buscarSala(idSala), atividade));
        }
        return false;
    }

    public boolean excluirAtividade(int codigo) {
        return controleAtividade.excluir(this.buscarAtividade(codigo));
    }

    public Atividade buscarAtividade(int codigo) {
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

    public boolean atividadeJaConcluida(int codigo) {
        return controleAtividade.buscarAtividade(codigo).getDataConclusao() != null;
    }

    public boolean alterarMateriaAtividade(int codigo, String materia) {
        return controleAtividade.alterarMateria(codigo, materia);
    }

    public boolean alterarValorAtividade(int codigo, double valor) {
        return controleAtividade.alterarValor(codigo, valor);
    }

    public List<Atividade> listarAtividadesDaSala(int codigoSala) {
        return controleSalaAtividade.listarAtividadesDaSala(controleSala.buscarSala(codigoSala));
    }

    public boolean atividadeExiste (int codigo) {
        return controleAtividade.buscarAtividade(codigo) != null;
    }

    public boolean atividadeJaAtribuida(int codigoAtividade, String username) {
        return controleUsuarioAtividade.atividadeJaAtribuida(
                this.buscarAtividade(codigoAtividade),
                this.buscarUsuario(username)
        );
    }

    public boolean adicionarAtividadeParaAluno(int idAtividade, String username) {
        if (!atividadeJaAtribuida(idAtividade, username))
            return controleUsuarioAtividade.adicionar(
                    UsuarioAtividade.getInstance(this.buscarUsuario(username), this.buscarAtividade(idAtividade))
            );
        else
            return false;
    }

    public boolean removerAtividadeParaAluno(Atividade atividade, Usuario usuarioAtual) {
        return controleUsuarioAtividade.remover(
                UsuarioAtividade.getInstance(usuarioAtual, atividade)
        );
    }

    public boolean marcarAtividadeConcluida(int codigo) {
        return controleUsuarioAtividade.alterarStatus(codigo, this.getUsuarioAtual().getUsername(), "Concluída");
    }

    public boolean marcarAtividadeNaoConcluida(int codigo) {
        return controleUsuarioAtividade.alterarStatus(codigo, this.getUsuarioAtual().getUsername(), "Não concluída");
    }

    public boolean removerAtividadesDeAlunoDaSala(int idSala) {
        return controleUsuarioAtividade.removerAtividadesDeAlunoDaSala(
                idSala,
                this.getUsuarioAtual().getUsername()
        );
    }

    // ============================================
    // Grupo
    // ============================================
    public boolean adicionarGrupo(Grupo grupo) {
        return controleGrupo.adicionar(grupo);
    }

    public boolean excluirGrupo(int codigo) {
        return controleGrupo.excluir(controleGrupo.buscarGrupo(codigo));
    }

    public Grupo buscarGrupo(int codigoGrupo) {
        return controleGrupo.buscarGrupo(codigoGrupo);
    }

    public boolean grupoExiste(int codigo) {
        return controleGrupo.buscarGrupo(codigo) != null;
    }

    public boolean grupoEhDaSala(int codigoGrupo, int codigoSala) {
        return controleGrupo.grupoEhDaSala(controleGrupo.buscarGrupo(codigoGrupo), controleSala.buscarSala(codigoSala));
    }

    public List<Grupo> listarGruposDaSala(int codigoSala) {
        return controleGrupo.listarGruposDaSala(controleSala.buscarSala(codigoSala));
    }

    public List<Usuario> listarParticipantesGrupo(int codigoGrupo) {
        return controleUsuarioGrupo.listarParticipantesGrupo(controleGrupo.buscarGrupo(codigoGrupo));
    }

    public boolean entrarGrupo(int codigoGrupo) {
        return controleUsuarioGrupo.entrarGrupo(
                this.getUsuarioAtual(),
                this.buscarGrupo(codigoGrupo)
        );
    }

    public boolean sairGrupo(int codigoGrupo) {
        return controleUsuarioGrupo.sairGrupo(
                this.getUsuarioAtual(),
                this.buscarGrupo(codigoGrupo)
        );
    }

    public boolean atualizarNomeGrupo(int codigo, String nome) {
        return controleGrupo.alterarNome(codigo, nome);
    }

    public boolean atualizarLiderGrupo(int codigo, String lider) {
        return controleGrupo.alterarLider(codigo, this.buscarUsuario(lider));
    }

    public boolean usuarioEstaNoGrupo(int codigoGrupo, String username) {
        return controleUsuarioGrupo.usuarioEstaNoGrupo(
                this.buscarUsuario(username),
                this.buscarGrupo(codigoGrupo)
        );
    }

    public boolean removerAlunoDeGrupoDaSala(int idSala) {
        return controleUsuarioGrupo.removerAlunoDeGrupoDaSala(
                controleSala.buscarSala(idSala)
        );
    }
}
