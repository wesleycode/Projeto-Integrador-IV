package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class IndexController implements Serializable {
    public String cadastrarNovoUsuario() {
        return "cadastroLogin?faces-redirect=true";
    }
}
