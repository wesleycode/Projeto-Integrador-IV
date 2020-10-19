package controller;

import model.bo.ProdutoBO;
import model.entities.Produto;
import utilities.Moeda;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PainelVendedorController implements Serializable {
    public PainelVendedorController(){}

    public String irParaCadastrarCategoria() {
        return "cadastrarmarca?faces-redirect=true";
    }
    public String irParaCadastarFornecedor() {
        return "cadastrarfornecedor?faces-redirect=true";
    }

}