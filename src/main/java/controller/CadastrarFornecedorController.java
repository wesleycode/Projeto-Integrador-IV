package controller;

import model.bo.FornecedorBO;
import model.bo.MarcaProdutoBO;
import model.entities.Fornecedor;
import model.entities.MarcaProduto;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CadastrarFornecedorController implements Serializable {

    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedorlist;

    public CadastrarFornecedorController() {
        fornecedor = new Fornecedor();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(List<Fornecedor> fornecedor) {
        this.fornecedorlist = fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String irParaPainelVendedor() {
        return "painelVendedor?faces-redirect=true";
    }

    public String irParaIndex() {
        return "index?faces-redirect=true";
    }

    public void cadastrarFornecedor() {
        try {
            if (new FornecedorBO().criar(getFornecedor())) {
                FacesMessages.info("Fornecedor cadastrado com sucesso");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

    public List<Fornecedor> getTodasFornecedorList() throws Exception {
        try {
            return new FornecedorBO().listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list de Fornecedor: " + e.getMessage());
        }
    }

}