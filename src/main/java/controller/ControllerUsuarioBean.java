package controller;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ControllerUsuarioBean implements Serializable {

    public void criarUsuario() {
        /*
        try {
            new ClienteDao().salvar(new Cliente());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */
    }

    public String irParaPagina2() {
        return "page2?faces-redirect=true";
    }

}
