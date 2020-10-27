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
    private Carrinho carrinho;
    private List<ItensCarrinho>itensCarrinhos;
    private List<FormaPagamento> formaPagamentoList;
    private Endereco endereco;
    private int tipoPessoaLogin;
    private Pedido pedido;

    public int getTipoPessoaLogin() {
        return tipoPessoaLogin;
    }

    public void setTipoPessoaLogin(int tipoPessoaLogin) {
        this.tipoPessoaLogin = tipoPessoaLogin;
    }

    public LoginController() {
        pessoa = new Pessoa();
        carrinho = new Carrinho();
        endereco = new Endereco();
        pedido = new Pedido();
        pessoa.getEndereco().setCidade(new Cidade());
        formaPagamentoList = listarTodasFormasDePagamento();
    }

    public List<FormaPagamento> getFormaPagamentoList() {
        return formaPagamentoList;
    }

    public void setFormaPagamentoList(List<FormaPagamento> formaPagamentoList) {
        this.formaPagamentoList = formaPagamentoList;
    }


    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<ItensCarrinho> getItensCarrinhos() {
        return itensCarrinhos;
    }

    public void setItensCarrinhos(List<ItensCarrinho> itensCarrinhos) {
        this.itensCarrinhos = itensCarrinhos;
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

    //Carrinho
    public String irParaCarrinho() {
        if(pessoa.getId() <= 0){//se não estiver logado
            return "cadastroLogin.xhtml?faces-redirect=true";
        }
        //não coloquei direto pq n quero passar o ID
        endereco.setCidade(pessoa.getEndereco().getCidade());
        endereco.setBairro(pessoa.getEndereco().getBairro());
        endereco.setCep(pessoa.getEndereco().getCep());
        endereco.setComplemento(pessoa.getEndereco().getComplemento());
        endereco.setNumero(pessoa.getEndereco().getNumero());
        endereco.setRua(pessoa.getEndereco().getRua());

        return "carrinho.xhtml?faces-redirect=true";}


    public List<FormaPagamento> listarTodasFormasDePagamento() {
        try {
            return new FormaPagamentoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
            return null;
        }
    }
    public void addcarrinho(Produto produto){
        ItensCarrinho itensCarrinho = new ItensCarrinho();
        itensCarrinho.setProduto(produto);
        itensCarrinho.setCarrinho(carrinho);
        itensCarrinho.setQuantidade(1);

        itensCarrinho.setValor((long) produto.getPreco());
        this.itensCarrinhos.add(itensCarrinho);
        atualizarDadosCarrinho();
        FacesMessages.info("Item adicionado ao carrinho!");
    }
    public void atualizarDadosCarrinho(){
        carrinho.setQuantidade(0);//reset
        carrinho.setValorTotal(0);//reset
        int x;
        for(x=0;x > itensCarrinhos.size();x++){
            carrinho.setValorTotal(carrinho.getValorTotal()+itensCarrinhos.get(x).getValor());
            carrinho.setQuantidade(carrinho.getQuantidade() + itensCarrinhos.get(x).getQuantidade());
        }
    }
    public void addQauntidadeIten(ItensCarrinho itensCarrinho){
        //Em teoria a quantidade Ja vai por automatico então sem passagem por parametro
        int x;
        for(x=0;x > itensCarrinhos.size();x++){
            if(itensCarrinho == itensCarrinhos.get(x)){
                itensCarrinhos.get(x).setValor(itensCarrinhos.get(x).getQuantidade() * itensCarrinhos.get(x).getQuantidade());
            }
        }
        atualizarDadosCarrinho();
        FacesMessages.info("Item removido do carrinho!");
    }
    public void removecarrinho(ItensCarrinho itensCarrinho){
        int x;
        for(x=0;x > itensCarrinhos.size();x++){
            if(itensCarrinho == itensCarrinhos.get(x)){
                itensCarrinhos.remove(x);
            }
        }
        atualizarDadosCarrinho();
        FacesMessages.info("Item removido do carrinho!");
    }
    public void cadastrarcomprar(){
        try {
            if(endereco.getRua() == pessoa.getEndereco().getRua()
                    && endereco.getNumero() == pessoa.getEndereco().getNumero()
                    && endereco.getBairro() == pessoa.getEndereco().getBairro()
                    && endereco.getCep() == pessoa.getEndereco().getCep()){
                endereco = pessoa.getEndereco();
                //Enfin se tu olhar o xhtml o select deixa a cidade e estado null
                //por isos essa verificação, Sim isso é uma puta gambiara n nego
                //Mas se funciona Ta valendo.
            }else if(endereco != pessoa.getEndereco()){//caso o usuario escolha outro endereço
                new EnderecoBO().criar(endereco);//endereço de Entrega
            }
        } catch (Exception e) {
            FacesMessages.error("Erro ao cadastrar Endereco: " + e.getMessage());
        }
        pedido.setPessoa(pessoa);
        pedido.setEndereco(endereco);
        try {
            new PedidoBO().criar(pedido);//cadastrar pacialmente pedido
        } catch (Exception e) {
            FacesMessages.error("Erro ao cadastrar Pedido: " + e.getMessage());
        }
        int x;
        for(x=0;x > itensCarrinhos.size();x++){
            try {
                ItensPedido itensPedido = new ItensPedido();

                itensPedido.setPedido(pedido);
                itensPedido.setProduto(itensCarrinhos.get(x).getProduto());
                itensPedido.setQuantidade(itensCarrinhos.get(x).getQuantidade());
                itensPedido.setValor(itensCarrinhos.get(x).getValor());

                new ItensPedidoBO().criar(itensPedido);
            } catch (Exception e) {
                FacesMessages.error("Erro ao cadastrar ItensPedido: " + e.getMessage());
            }
            FacesMessages.info("ItensPedido Cadastrado com sucesso");
        }
        try {
            pedido.setValorTotal(carrinho.getValorTotal());
            pedido.setQuantidade(carrinho.getQuantidade());
            new PedidoBO().alterar(pedido); //finalização de cadastro
        } catch (Exception e) {
            FacesMessages.error("Erro ao cadastrar Pedido: " + e.getMessage());
        }
        FacesMessages.info("Pedido Cadastrado com sucesso");
    }


}