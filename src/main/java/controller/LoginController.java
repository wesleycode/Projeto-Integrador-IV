package controller;

import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private Pessoa pessoa;
    private int tipoPessoaLogin;

    public int getTipoPessoaLogin() {
        return tipoPessoaLogin;
    }

    public void setTipoPessoaLogin(int tipoPessoaLogin) {
        this.tipoPessoaLogin = tipoPessoaLogin;
    }

    public LoginController() {
        pessoa = new Pessoa();
        pessoa.getEndereco().setCidade(new Cidade());
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public String deslogar() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        pessoa = null;
        return irParaIndex();
    }
    public String logar() {
        switch (getTipoPessoaLogin()) {
            case 0:
                FacesMessages.info("Selecione um tipo de login!");
            case 1:
                return logarCliente();
            case 2:
                return logarVendedor();
            case 3:
                return logarAdministrador();
            default:
                return null;
        }
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

    public String irParaIndex() { return "index.xhtml?faces-redirect=true"; }

    public void cadastrarUsuario() {
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(1);
        if(pessoa.getEndereco().getCidade().getId() < 0){
            FacesMessages.error("Informe a Cidade");
        }else if(pessoa.getEndereco().getCidade().getEstado().getId() < 0){
            FacesMessages.error("Informe o Estado");
        }else {
            try {
                pessoa.setEndereco(new EnderecoBO().listarultimoendereco());
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
                System.out.println("Endereço ID: "+pessoa.getEndereco().getId());
                if (new PessoaBO().criar(pessoa)) {
                    FacesMessages.info("Usuário Cadastrado com sucesso");
                } else {
                    FacesMessages.error("Erro ao cadastrar usuário");
                }
            } catch (Exception e) {
                FacesMessages.error("Erro: " + e.getMessage());
            }
        }
    }



}