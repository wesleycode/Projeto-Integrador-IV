package controller;

import model.bo.CidadeBO;
import model.bo.EnderecoBO;
import model.bo.EstadoBO;
import model.bo.PessoaBO;
import model.entities.Cidade;
import model.entities.Estado;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PainelAdministradorController implements Serializable {
    private Pessoa pessoa;
    private int tipoPessoaLogin;
    private int tipoPessoaCadastro;

    public int getTipoPessoaLogin() {
        return tipoPessoaLogin;
    }

    public void setTipoPessoaLogin(int tipoPessoaLogin) {
        this.tipoPessoaLogin = tipoPessoaLogin;
    }

    public int getTipoPessoaCadastro() {
        return tipoPessoaCadastro;
    }

    public void setTipoPessoaCadastro(int tipoPessoaCadastro) {
        this.tipoPessoaCadastro = tipoPessoaCadastro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PainelAdministradorController() {
        pessoa = new Pessoa();
    }

    public String irParaCadastarusuário() {
        return "cadastrarusuarioViaAdm?faces-redirect=true";
    }

    public List<Estado> listarTodosOsEstados() {
        try {
            return new EstadoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    public List<Cidade> listarTodosAsCidadesPorEstado(Estado estado) {
        try {
            return new CidadeBO().listarCidadePorEstado(estado);
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    private void cadastrarUsuario() {
        // Grava o endereco //
        try {
            if (new EnderecoBO().criar(pessoa.getEndereco())) {
                FacesMessages.info("Endereco cadastrado com sucesso");
            } else {
                FacesMessages.error("Erro ao cadastrar endereço do usuario");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        // Grava o cliente //
        try {
            if (new PessoaBO().criar(pessoa)) {
                FacesMessages.info("Usuário Cadastrado com sucesso");
            } else {
                FacesMessages.error("Erro ao cadastrar usuário");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

    private void cadastrarNovoCliente() {
        // Sempre ao CADASTRAR um novo cliente o status dele é ativo por padrão //
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(1);
        cadastrarUsuario();
    }

    private void cadastrarNovoVendedor() {
        // Sempre ao CADASTRAR um novo vendedor o status dele é ativo por padrão //
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(2);
        cadastrarUsuario();
    }

    private void cadastrarNovoAdministrador() {
        // Sempre ao CADASTRAR um novo vendedor o status dele é ativo por padrão //
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(3);
        cadastrarUsuario();
    }


    private String logarAdministrador() {
        pessoa.setTipoUsuario(3);

        if (new PessoaBO().logarPessoa(pessoa).equals("OK")) {
            try {
                pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(),pessoa.getSenha());
            } catch (Exception e) {
                FacesMessages.error("Erro ao logar: " + e.getMessage());
                return "";
            }
            FacesMessages.info("Logado com sucesso");
            return "/painelADM.xhtml?faces-redirect=true";
        } else {
            FacesMessages.info("Usuário e/ou senha inválidos");
            return "";
        }
    }

    private String logarVendedor() {
        pessoa.setTipoUsuario(2);
        if (new PessoaBO().logarPessoa(pessoa).equals("OK")) {
            try {
                pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(),pessoa.getSenha());
            } catch (Exception e) {
                FacesMessages.error("Erro ao logar: " + e.getMessage());
                return "";
            }
            FacesMessages.info("Logado com sucesso", "detail message");
            return "/painelVendedor?faces-redirect=true";
        } else {
            FacesMessages.info("Usuário e/ou senha inválidos");
            return "";
        }
    }

    private String logarCliente() {
        pessoa.setTipoUsuario(1);
        if (new PessoaBO().logarPessoa(pessoa).equals("OK")) {
            try {
                pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(),pessoa.getSenha());
            } catch (Exception e) {
                FacesMessages.error("Erro ao logar: " + e.getMessage());
                return "";
            }
            FacesMessages.info("Logado com sucesso", "detail message");
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesMessages.info("Usuário e/ou senha inválidos");
            return "";
        }
    }
}