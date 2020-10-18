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
public class produtoController implements Serializable {

    public produtoController() {

    }

    public String getFormatarPreco(Produto produto) {
        return Moeda.converterLongParaDinheiroStringPadraoBrasil((long) (produto.getPreco()));
    }

    public String getPrecoFormatado10x(Produto produto) {
        return Moeda.converterLongParaDinheiroStringPadraoBrasil((long) (produto.getPreco() / 10));
    }

}
