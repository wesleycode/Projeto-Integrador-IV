package controller;

import model.bo.EstadoBO;
import model.bo.ProdutoBO;
import model.dao.EstadoDao;

import model.entities.Estado;
import model.entities.Produto;
import utilities.Moeda;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class IndexController implements Serializable {

    private List<Produto> todosProdutosList;
    private Produto produto;

    public void setTodosProdutosList(List<Produto> todosProdutosList) {
        this.todosProdutosList = todosProdutosList;
    }

    public Produto getProduto() {
        return produto;
    }

    public IndexController() {

    }

    public String getPrecoFormatado10x(Produto produto) {
        return Moeda.converterLongParaDinheiroStringPadraoBrasil((long) (produto.getPreco() / 10));
    }

    public List<Produto> getTodosProdutosList() throws Exception {
        try {
            return new ProdutoBO().listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list de produtos: " + e.getMessage());
        }
    }

    public String irParaPesquisaProdutos() {
        return "pesquisaProdutos?faces-redirect=true";
    }

    public String irParaUsuarios() { return "cadastroLogin?faces-redirect=true"; }

}
