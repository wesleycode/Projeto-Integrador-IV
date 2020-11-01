package controller;

import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import utilities.Tempo;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DetalhesProdutoController implements Serializable {

    private Produto produto;
    private Pessoa pessoa;
    private Pedido pedido;
    private Avaliacao avaliacaoUsuario;
    private List<FormaPagamento> formaPagamentoList;
    private List<Avaliacao> avaliacaoList;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        pedido = new Pedido();
        formaPagamentoList = listarTodasFormasDePagamento();
        avaliacaoList = listarAvaliacaoDeProdutoSelecionado();
    }

    public List<FormaPagamento> listarTodasFormasDePagamento() {
        try {
            return new FormaPagamentoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
            return null;
        }
    }

    public String irparacadastraravaliacao(){
        if(pessoa == null){
            return RedirecionamentoController.irParaCadastro();
        }
        return RedirecionamentoController.irParaCadastrarAvaliacao();
    }

    public void criarAvaliacao() {
        try {
            Avaliacao avaliacaoBusca = new AvaliacaoBO().listarAvaliaçaodeprodutoexiste(pessoa,produto);
            avaliacaoUsuario.setDataPostado(Tempo.getDataAtualSql());
            avaliacaoUsuario.setProduto(produto);
            avaliacaoUsuario.setPessoa(pessoa);
            if(avaliacaoBusca == new Avaliacao()){
                new AvaliacaoBO().criar(avaliacaoUsuario);
                FacesMessages.info("Avaliação realizada! agradecemos seu feedback!");
            }else{
                avaliacaoUsuario.setId(avaliacaoBusca.getId());
                new AvaliacaoBO().alterar(avaliacaoUsuario);
                FacesMessages.info("Avaliação realizada! agradecemos seu feedback!");
            }
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
