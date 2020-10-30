package controller;

import model.bo.*;
import model.dao.PedidoDao;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class DetalhesProdutoController implements Serializable {

    private Produto produto;
    private Endereco endereco;
    private Pessoa pessoa;
    private Carrinho carrinho;
    private Pedido pedido;
    private Avaliacao avaliacaoUsuario;
    private List<ItensCarrinho> itensCarrinhoList;
    private List<FormaPagamento> formaPagamentoList;
    private List<Avaliacao> avaliacaoList;
    private List<Pedido> pedidoList;
    private int quantidadeItem;

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Avaliacao getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(Avaliacao avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }

    public List<ItensCarrinho> getItensCarrinhoList() {
        return itensCarrinhoList;
    }

    public void setItensCarrinhoList(List<ItensCarrinho> itensCarrinhoList) {
        this.itensCarrinhoList = itensCarrinhoList;
    }

    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    public List<FormaPagamento> getFormaPagamentoList() {
        return formaPagamentoList;
    }

    public void setFormaPagamentoList(List<FormaPagamento> formaPagamentoList) {
        this.formaPagamentoList = formaPagamentoList;
    }

    public DetalhesProdutoController() {
        produto = (Produto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("produto");
        pessoa = (Pessoa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pessoa");
        avaliacaoUsuario = new Avaliacao();
        carrinho = new Carrinho();
        endereco = new Endereco();
        pedido = new Pedido();
        itensCarrinhoList = new ArrayList<>();
        formaPagamentoList = listarTodasFormasDePagamento();
        avaliacaoList = listarAvaliacaoDeProdutoSelecionado();
        pedidoList = listarPedidodePessoa();
    }

    public String irParaPedidos() {
        return RedirecionamentoController.irParaMeusPedidos();
    }
    public String irParaCarrinho() {

        try {
            if (pessoa.getId() > 0) {
                atualizarDadosCarrinho();
                //não coloquei direto pq n quero passar o ID
                endereco.setCidade(pessoa.getEndereco().getCidade());
                endereco.setBairro(pessoa.getEndereco().getBairro());
                endereco.setCep(pessoa.getEndereco().getCep());
                endereco.setComplemento(pessoa.getEndereco().getComplemento());
                endereco.setNumero(pessoa.getEndereco().getNumero());
                endereco.setRua(pessoa.getEndereco().getRua());
                return RedirecionamentoController.irParaCarrinho();
            } else {
                return RedirecionamentoController.irParaCadastro();
            }
        } catch (Exception e) {
            return RedirecionamentoController.irParaCadastro();
        }

    }

    public List<FormaPagamento> listarTodasFormasDePagamento() {
        try {
            return new FormaPagamentoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
            return null;
        }
    }

    public List<Pedido> listarPedidodePessoa() {
        try {
            return new PedidoBO().listarpedidosDaPessoa(pessoa);
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
            return null;
        }
    }

    public String adicionarProdutoNoCarrinho() {

        ItensCarrinho itemCarrinho = new ItensCarrinho();
        itemCarrinho.setProduto(produto);
        itemCarrinho.setCarrinho(carrinho);
        itemCarrinho.setQuantidade(quantidadeItem);
        itemCarrinho.setValor((long) produto.getPreco());

        try {
            if (new ItensCarrinhoBO().valida(itemCarrinho)) {
                itensCarrinhoList.add(itemCarrinho);
                atualizarDadosCarrinho();
                FacesMessages.info("Item adicionado ao carrinho!");
                System.out.println("BELEZA... AGORA VAI PRO CARRINHO!!!");
                return irParaCarrinho();
            }
        } catch (Exception e) {
            FacesMessages.error("Erro : " + e.getMessage());
        }
        return "";
    }

    public void atualizarDadosCarrinho() {
        carrinho.setQuantidade(0);//reset
        carrinho.setValorTotal(0);//reset
        for (ItensCarrinho itensCarrinho : itensCarrinhoList) {
            carrinho.setValorTotal(carrinho.getValorTotal() + itensCarrinho.getValor());
            carrinho.setQuantidade(carrinho.getQuantidade() + itensCarrinho.getQuantidade());
        }
    }

    public void addQuantidadeItem(ItensCarrinho itensCarrinho) {
        //Em teoria a quantidade Ja vai por automatico então sem passagem por parametro
        for (ItensCarrinho value : itensCarrinhoList) {
            if (itensCarrinho == value) {
                value.setValor(value.getQuantidade() * value.getQuantidade());
            }
        }
        atualizarDadosCarrinho();
        FacesMessages.info("Item removido do carrinho!");
    }

    public void removeCarrinhoDeItensCarrinho(ItensCarrinho itensCarrinho) {
        for (int x = 0; x < itensCarrinhoList.size(); x++) {
            if (itensCarrinho == itensCarrinhoList.get(x)) {
                itensCarrinhoList.remove(x);
            }
        }
        atualizarDadosCarrinho();
        FacesMessages.info("Item removido do carrinho!");
    }

    public void cadastrarOuComprar() {
        try {
            if (endereco.getRua().equals(pessoa.getEndereco().getRua())
                    && endereco.getNumero() == pessoa.getEndereco().getNumero()
                    && endereco.getBairro().equals(pessoa.getEndereco().getBairro())
                    && endereco.getCep().equals(pessoa.getEndereco().getCep())) {
                endereco = pessoa.getEndereco();
                //Enfin se tu olhar o xhtml o select deixa a cidade e estado null
                //por isos essa verificação, Sim isso é uma puta gambiara n nego
                //Mas se funciona Ta valendo.
            } else if (endereco != pessoa.getEndereco()) {//caso o usuario escolha outro endereço
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
        for (int x = 0; x <= itensCarrinhoList.size(); x++) {
            try {
                ItensPedido itensPedido = new ItensPedido();

                itensPedido.setPedido(pedido);
                itensPedido.setProduto(itensCarrinhoList.get(x).getProduto());
                itensPedido.setQuantidade(itensCarrinhoList.get(x).getQuantidade());
                itensPedido.setValor(itensCarrinhoList.get(x).getValor());

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

    public void criarAvaliacao() {
        try {
            new AvaliacaoBO().criar(avaliacaoUsuario);//cadastrar parcialmente pedido
        } catch (Exception e) {
            FacesMessages.error("Erro ao cadastrar Avaliacao: " + e.getMessage());
        }
    }

    private List<Avaliacao> listarAvaliacaoDeProdutoSelecionado() {
        try {
            return new AvaliacaoBO().listarAvaliacaoPorProduto(produto);
        } catch (Exception e) {
            FacesMessages.error("Erro ao selecionar avaliações: " + e.getMessage());
            return null;
        }
    }

    public String getTipoDeNota(Avaliacao avaliacao) {
        switch (avaliacao.getNota()) {
            case 1:
                return "Muito Ruim";
            case 2:
                return "Ruim";
            case 3:
                return "Ok";
            case 4:
                return "Bom";
            case 5:
                return "Muito Bom";
            default:
                return "Não identificado";
        }
    }

}
