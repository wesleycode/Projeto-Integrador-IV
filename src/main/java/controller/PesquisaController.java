package controller;


import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PesquisaController implements Serializable {

    private List<Produto> todosProdutosList;
    private Produto produto;
    private String inputPesquisa;
    private Categoria categoriaSelecionada;
    private Produto produtoselecionado;

    public void setTodosProdutosList(List<Produto> todosProdutosList) { this.todosProdutosList = todosProdutosList; }

    public Produto getProduto() { return produto; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public Produto getProdutoselecionado() { return produtoselecionado; }

    public void setProdutoselecionado(Produto produtoselecionado) { this.produtoselecionado = produtoselecionado; }

    public String getInputPesquisa() {
        return inputPesquisa;
    }

    public void setInputPesquisa(String inputPesquisa) {
        this.inputPesquisa = inputPesquisa;
    }

    public Categoria getCategoriaSelecionada() { return categoriaSelecionada; }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) { this.categoriaSelecionada = categoriaSelecionada; }

    public String irParaPesquisaProdutos() {
        //listarTodosOsProdutosPorCategoria();
        return "filtrarProdutos?faces-redirect=true";
    }

    public List<Produto> getListaDeTodosOsProdutosPorCategoria() {
        try {
            return new ProdutoBO().listarPorCategoria(categoriaSelecionada);
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    public List<Categoria> getListaDeTodasAsCategorias() {
        try {
            return new CategoriaBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    public PesquisaController() {
        this.categoriaSelecionada = new Categoria();
    }

}
