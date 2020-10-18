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
public class SobrenosController implements Serializable {
    public SobrenosController(){

    }
    public String irParaPoliticas() {
        return "politicas?faces-redirect=true";
    }

    public String irParaFornecedores() {
        return "fornecedores?faces-redirect=true";
    }

}
