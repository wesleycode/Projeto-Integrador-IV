package controller;

import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import utilities.Strings;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

@Named
@RequestScoped
public class AlterarProdutoController implements Serializable {

    private float preco;
    private String emEstoque;
    private Produto produtoSelecionado;
    private List<Produto> todosOsProdutosList;
    private List<MarcaProduto> marcalist;
    private List<Categoria> categoriaList;
    private List<Fornecedor> fornecedorList;

    public void setEmEstoque(String emEstoque) {
        if (emEstoque.equalsIgnoreCase("S") || emEstoque.equalsIgnoreCase("true")) {
            this.produtoSelecionado.setEmEstoque(true);
        } else {
            this.produtoSelecionado.setEmEstoque(false);
        }
    }

    public String getEmEstoque() {
        return emEstoque;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Produto> getTodosOsProdutosList() {
        return todosOsProdutosList;
    }

    public void setTodosOsProdutosList(List<Produto> todosOsProdutosList) {
        this.todosOsProdutosList = todosOsProdutosList;
    }

    public List<MarcaProduto> getMarcalist() {
        return marcalist;
    }

    public void setMarcalist(List<MarcaProduto> marcalist) {
        this.marcalist = marcalist;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

    public AlterarProdutoController() {
        produtoSelecionado = new Produto();
        todosOsProdutosList = listarTodosOsProdutos();
        categoriaList = listarCategoria();
        marcalist = listarMarca();
        fornecedorList = listarFornecedor();
    }

    public List<MarcaProduto> listarMarca() {
        try {
            return new MarcaProdutoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Categoria> listarCategoria() {
        try {
            return new CategoriaBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Fornecedor> listarFornecedor() {
        try {
            return new FornecedorBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Produto> listarTodosOsProdutos() {
        try {
            return new ProdutoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public void alterarProduto() {
        try {
            produtoSelecionado.setFotoProduto(new ProdutoBO()
                    .getById(produtoSelecionado.getId())
                    .getFotoProduto());
            new ProdutoBO().alterar(produtoSelecionado);
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

}