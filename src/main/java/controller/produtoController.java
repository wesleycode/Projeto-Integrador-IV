package controller;

import model.bo.ProdutoBO;
import model.entities.Produto;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class produtoController implements Serializable {

    public produtoController() {

    }

    public String getFormatarPreco(Produto produto) {
        return ProdutoBO.getPrecoFormatado(produto.getPreco());
    }

    public String getPrecoFormatado10x(Produto produto) {
        return ProdutoBO.getPrecoParceladoEm10Vezes(produto.getPreco());
    }

}
