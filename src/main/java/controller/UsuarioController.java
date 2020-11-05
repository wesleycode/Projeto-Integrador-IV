package controller;

import model.bo.PessoaBO;
import model.dao.GenericDao;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;

@Named
@SessionScoped
public class UsuarioController implements Serializable {

    public String irParaPagina2() {
        return "page2?faces-redirect=true";
    }

}//Aqui jas uma parte do passado, o inicio de Tudo, pf faça silencio
