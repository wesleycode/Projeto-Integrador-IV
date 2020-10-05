package controller;

import model.bo.ClienteBO;
import model.entities.Cliente;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
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

    public String logarCliente() {
        if (new ClienteBO().logar(cliente).equals("OK")) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage("Logado com sucesso"));
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage("Usuário e/ou senha inválidos"));
            return "";
        }
    }

    public String deslogar() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        cliente = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public void cadastrarCliente() {

        System.out.println("ID: " + cliente.getId());
        System.out.println("EMAIL: " + cliente.getEmail());
        System.out.println("SENHA: " + cliente.getSenha());
        System.out.println("NOME: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("TELEFONE: " + cliente.getTelefone());
        System.out.println("DATA NASCIMENTO: " + cliente.getDataNascimento());
        System.out.println("ENDEREÇO: " + cliente.getEndereco());

//        String message = new ClienteBO().criar(cliente);
//        if (message.equals("OK")) {
//            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage("Usuário Cadastrado com sucesso"));
//        } else {
//            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(message));
//        }
    }

}
