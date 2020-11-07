package controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class PainelVendedorController implements Serializable {

    public PainelVendedorController() {

    }

    public String irParaCadastrarCategoria() {
        return "cadastrarMarca?faces-redirect=true";
    }

    public String irParaCadastarFornecedor() {
        return "cadastrarFornecedor?faces-redirect=true";
    }

    public String irParaCadastarProduto() { return "cadastrarProduto?faces-redirect=true"; }

    public String irParaModificarProduto() { return "modificarProduto?faces-redirect=true"; }

}