package controller;

import model.entities.Cliente;
import model.entities.Pessoa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginController implements Serializable {

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void nomeUsuario() {
        System.out.println("Nome: " + this.getCliente().getNome());
    }
}
