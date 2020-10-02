package controller;

import model.bo.ClienteBO;
import model.dao.ClienteDao;
import model.dao.GenericDao;
import model.entities.Cliente;
import model.entities.Pessoa;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginController implements Serializable {

    private Cliente cliente;

    public LoginController() {
        cliente = new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String logar() {
        String message = new ClienteBO().logar(cliente);
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(message));
        return message;
    }

}
