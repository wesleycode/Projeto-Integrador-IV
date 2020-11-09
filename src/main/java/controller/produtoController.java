package controller;

import model.bo.CategoriaBO;
import model.bo.PessoaBO;
import model.bo.ProdutoBO;
import model.entities.Categoria;
import model.entities.Pessoa;
import model.entities.Produto;
import net.bootsfaces.utils.FacesMessages;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class produtoController implements Serializable {

    private List<Produto> listaTodosOsProdutos;
    private Categoria categoriaSelecionada;
    private List<Categoria> listaTodasAsCategorias;
    private String filtertext;

    public String getFiltertext() {
        return filtertext;
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
        listaTodosOsProdutos = preencherTabela();
        listaTodasAsCategorias = listarTodasAsCategorias();
        filtertext = "";
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
            return new ProdutoBO().listarProdutos(categoriaSelecionada, filtertext);
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

    public String irParaDetalhesDeProduto(Produto produto) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produto", produto);
        return RedirecionamentoController.irParaProdutoSelecionado();
    }

    public String irDiretoParaCarrinho(Produto produto) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produto", produto);
        return RedirecionamentoController.irParaCarrinho();
    }

}
