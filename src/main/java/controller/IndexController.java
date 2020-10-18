package controller;

import model.bo.EstadoBO;
import model.bo.ProdutoBO;
import model.dao.EstadoDao;

import model.entities.Estado;
import model.entities.Produto;
import utilities.Moeda;
import javax.faces.event.ActionEvent;
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

    public List<Produto> getTodosProdutosList() throws Exception {
        try {
            return new ProdutoBO().listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list de produtos: " + e.getMessage());
        }
    }
    public String irParaPoliticas() {
        return "politicas?faces-redirect=true";
    }

    public String irParaFornecedores() {
        return "fornecedores?faces-redirect=true";
    }

    public String irParaSobrenos() {
        return "sobrenos?faces-redirect=true";
    }

    public String getRedirecionamentoParaCadastro() {
        return "cadastroLogin?faces-redirect=true";
    }


}
