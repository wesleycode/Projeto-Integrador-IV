package controller;

import model.bo.EstadoBO;
import model.bo.FornecedorBO;
import model.bo.ProdutoBO;
import model.dao.EstadoDao;

import model.entities.Estado;
import model.entities.Fornecedor;
import model.entities.Produto;
import utilities.Moeda;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FornecedoresController implements Serializable {
    private List<Fornecedor> fornecedors;
    public FornecedoresController() throws Exception {
        try {
            setFornecedors(getTodosForencedoresList());
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list de fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedor> getFornecedors(){
        return fornecedors;
    }

    public void setFornecedors(List<Fornecedor> fornecedors) {
        this.fornecedors = fornecedors;
    }

    public List<Fornecedor> getTodosForencedoresList() throws Exception {
        try {
            return new FornecedorBO().listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list de produtos: " + e.getMessage());
        }
    }
    public String irParaSobrenos() {
        return "sobrenos?faces-redirect=true";
    }


}
