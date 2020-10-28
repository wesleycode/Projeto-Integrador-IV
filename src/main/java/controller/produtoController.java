package controller;

import model.bo.AvaliacaoBO;
import model.bo.CategoriaBO;
import model.bo.ProdutoBO;
import model.dao.ProdutoDao;
import model.entities.Avaliacao;
import model.entities.Categoria;
import model.entities.Produto;
import net.bootsfaces.utils.FacesMessages;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class produtoController implements Serializable {

    private Produto produtoSelecionado;
    private List<Produto> listaTodosOsProdutos;
    private Categoria categoriaSelecionada;
    private List<Categoria> listaTodasAsCategorias;
    private List<Avaliacao> avaliacaoList;
    private String filtertext;

    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    public String getFiltertext() {
        return filtertext;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public List<Produto> getListaTodosOsProdutos() {
        return listaTodosOsProdutos;
    }

    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public List<Categoria> getListaTodasAsCategorias() {
        return listaTodasAsCategorias;
    }

    public void setFiltertext(String filtertext) {
        this.filtertext = filtertext;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public void setListaTodosOsProdutos(List<Produto> listaTodosOsProdutos) {
        this.listaTodosOsProdutos = listaTodosOsProdutos;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public void setListaTodasAsCategorias(List<Categoria> listaTodasAsCategorias) {
        this.listaTodasAsCategorias = listaTodasAsCategorias;
    }

    public produtoController() {
        categoriaSelecionada = new Categoria();
        produtoSelecionado = new Produto();
        listaTodosOsProdutos = preencherTabela();
        listaTodasAsCategorias = listarTodasAsCategorias();
        filtertext = "";

        try {
            produtoSelecionado = new ProdutoDao().listarOProdutoAqui();
            avaliacaoList = this.listarAvaliacaoDeProdutoSelecionado();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());//Remover isso quando for atribuir os produtos corretamente com o metodo na DAO
        }
    }

    public String getFormatarPreco(Produto produto) {
        return ProdutoBO.getPrecoFormatado(produto.getPreco());
    }

    public String getPrecoFormatado10x(Produto produto) {
        return ProdutoBO.getPrecoParceladoEm10Vezes(produto.getPreco());
    }

    private List<Categoria> listarTodasAsCategorias() {
        try {
            return new CategoriaBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    private List<Produto> preencherTabela() {
        try {
            return new ProdutoBO().listarProdutos(categoriaSelecionada,filtertext);
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    public void filtrarProdutos() {
        listaTodosOsProdutos = preencherTabela();
    }

    public void resetarTabela() {
        try {
            listaTodosOsProdutos = new ProdutoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }
    public List<Avaliacao> listarAvaliacaoDeProdutoSelecionado() {
        try {
            return new AvaliacaoBO().listarAvaliacaoPorProduto(produtoSelecionado);
        } catch (Exception e) {
            FacesMessages.error("Erro ao selecionar avaliações: " + e.getMessage());
            return null;
        }
    }
    public String retornartiponota(Avaliacao avaliacao){
        switch (avaliacao.getNota()){
            case 1:
                return "Muito Ruim";
            case 2:
                return  "Ruim";
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
    public String irParaProdudoselecionado() {

        avaliacaoList = this.listarAvaliacaoDeProdutoSelecionado();
        return "produtoselecionado?faces-redirect=true";
    }

}
