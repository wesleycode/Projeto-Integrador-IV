package controller;

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
        if (UsuarioController.validarLogin(cliente.getNome(), cliente.getSenha())) {
            System.out.println("Encontrou o usuario");
            return "OK";
        }
        FacesContext.getCurrentInstance().addMessage("form",
                new FacesMessage("Usuario/senha invalidos"));
        return "";
    }

}
