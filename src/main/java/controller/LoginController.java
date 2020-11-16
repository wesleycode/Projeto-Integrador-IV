package controller;

import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import utilities.Tempo;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private Estado estadoSelecionado;
    private Cidade cidadeSelecionada;
    private Pessoa vendedorparaComissao;
    private Pessoa pessoa;
    private Carrinho carrinho;
    private Avaliacao avaliacaoUsuario;
    private Endereco endereco;
    private int tipoPessoaLogin;
    private int cont;
    private String nomeBotao;

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public Cidade getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(Cidade cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public Pessoa getVendedorparaComissao() {
        return vendedorparaComissao;
    }

    public void setVendedorparaComissao(Pessoa vendedorparaComissao) {
        this.vendedorparaComissao = vendedorparaComissao;
    }

    public String getNomeBotao() {
        return nomeBotao;
    }

    public void setNomeBotao(String nomeBotao) {
        this.nomeBotao = nomeBotao;
    }

    public int getTipoPessoaLogin() {
        return tipoPessoaLogin;
    }

    public void setTipoPessoaLogin(int tipoPessoaLogin) {
        this.tipoPessoaLogin = tipoPessoaLogin;
    }

    public LoginController() {
        pessoa = new Pessoa();
        pessoa.setDataNascimento(Tempo.getDataAtualSql());
        endereco = new Endereco();
        estadoSelecionado = new Estado();
        cidadeSelecionada = new Cidade();
        vendedorparaComissao = new Pessoa();
        nomeBotao = "Cancelar Conta";
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Avaliacao getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(Avaliacao avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }

    public List<Estado> listarTodosOsEstados() {
        try {
            return new EstadoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    public String irParaIndexComVendedor() {
        try {
            vendedorparaComissao = new PessoaBO().getById(pessoa.getId());
            pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(), pessoa.getSenha());
            if (pessoa.getTipoUsuario() > 1) {
                FacesMessages.error("Este Usuário Possui Capacidades de Compras!");
                pessoa = vendedorparaComissao;
                return null;
            }//Serve para vendedores não usarem como formas de "Descontos" para suas compras

            return RedirecionamentoController.irParaIndex();

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
        return RedirecionamentoController.irParaIndex();
    }

    public void cadastrarUsuario() {

        try {
            long lastId = new EnderecoBO().getLastId();
            if (lastId == -1) {
                endereco.setId(1);
            } else {
                endereco.setId(lastId + 1);
            }
            endereco.setCidade(cidadeSelecionada);
            pessoa.setEndereco(endereco);
            if (new EnderecoBO().criar(pessoa.getEndereco())) {
                FacesMessages.info("Endereco cadastrado com sucesso");
            } else {
                FacesMessages.error("Erro ao cadastrar endereço do usuario");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }

        try {
            long lastId = new PessoaBO().getLastId();
            if (lastId == -1) {
                pessoa.setId(1);
            } else {
                pessoa.setId(lastId + 1);
            }
            pessoa.setAtivo(true);
            pessoa.setTipoUsuario(1);
            if (new PessoaBO().criar(pessoa)) {
                FacesMessages.info("Usuário Cadastrado com sucesso");
            } else {
                FacesMessages.error("Erro ao cadastrar usuário");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

    public void CacelarConta() {
        if (cont == 0) {
            cont++;
            nomeBotao = "Tem Certeza?";
        } else {
            cont = 0;
            nomeBotao = "Cancelar Conta";
            if (pessoa == new Pessoa()) {
                FacesMessages.error("É necessário estar Logado!!!");
            } else if (pessoa.getTipoUsuario() > 1) {
                FacesMessages.error("Sua Conta possui privilégios Demais! peça a um ADM o Cancelamento");
            } else {
                pessoa.setAtivo(false);
                try {
                    new PessoaBO().alterar(pessoa);
                    FacesMessages.info("Sua Conta foi desativada com sucesso!");
                    deslogar();
                } catch (Exception e) {
                    FacesMessages.error("Erro ao cancelar: Você não está logado!");
                    FacesMessages.error("Erro: " + e.getMessage());
                }
            }
        }
    }

    public String logar() {
        switch (getTipoPessoaLogin()) {
            case 0:
                FacesMessages.info("Selecione uma forma de Pagmanto!");
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
                pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(), pessoa.getSenha());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa);
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
                pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(), pessoa.getSenha());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa);
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
                pessoa = new PessoaBO().getByEmailandsenha(pessoa.getEmail(), pessoa.getSenha());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa);
            } catch (Exception e) {
                FacesMessages.error("Erro ao logar: " + e.getMessage());
                return "";
            }
            FacesMessages.info("Logado com sucesso", "detail message");
            return RedirecionamentoController.irParaIndex();
        } else {
            FacesMessages.info("Usuário e/ou senha inválidos");
            return "";
        }
    }

}