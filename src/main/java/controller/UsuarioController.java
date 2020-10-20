package controller;

import model.dao.GenericDao;
import model.entities.Pessoa;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

    public String irParaPagina2() {
        return "page2?faces-redirect=true";
    }

}
