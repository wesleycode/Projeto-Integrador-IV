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

    private int totalDePaginas = 8;
    private int paginaAtual = 1;
    private int contadorDePaginas;

    public void setTodosProdutosList(List<Produto> todosProdutosList) {
        this.todosProdutosList = todosProdutosList;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setTotalDePaginas(int totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public void setContadorDePaginas(int contadorDePaginas) {
        this.contadorDePaginas = contadorDePaginas;
    }

    public IndexController() {

    }

    public String getPrecoFormatado10x(Produto produto) {
        return Moeda.converterLongParaDinheiroStringPadraoBrasil((long) (produto.getPreco() / 10));
    }

    public List<Produto> getTodosProdutosList() throws Exception {
        try {
            return new ProdutoBO().listarProdutosEntre(paginaAtual, totalDePaginas);
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list de produtos: " + e.getMessage());
        }
    }

    public void proximaPagina() throws Exception {
        if (paginaAtual == getContadorDePaginas()) {
            paginaAtual = 1;
        } else {
            paginaAtual++;
        }
    }

    public void paginaAnterior() throws Exception {
        if (paginaAtual <= 1) {
            paginaAtual = getContadorDePaginas();
        } else {
            paginaAtual--;
        }
    }

    public int getContadorDePaginas() throws Exception {
        try {
            return (int) Math.ceil(new ProdutoBO().listarTodos().size() / (double) totalDePaginas);
        } catch (Exception e) {
            throw new Exception("Erro ao retornar o numero do contador de paginas: " + e.getMessage());
        }
    }

}
