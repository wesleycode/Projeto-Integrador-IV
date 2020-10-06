package controller;

import model.bo.CidadeBO;
import model.bo.ClienteBO;
import model.bo.EstadoBO;
import model.entities.Cidade;
import model.entities.Cliente;
import model.entities.Estado;
import utilities.Datas;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private Cliente cliente;

    // Contrutor //
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

    public List<Estado> listarTodosOsEstados() {
        try {
            return new EstadoBO().listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public List<Cidade> listarTodosAsCidadesPorEstado(Estado estado) {
        try {
            return new CidadeBO().listarCidadePorEstado(estado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
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

        System.out.println("ENDEREÇO NUMERO: " + cliente.getEndereco().getId());
        System.out.println("ENDEREÇO RUA: " + cliente.getEndereco().getRua());
        System.out.println("ENDEREÇO BAIRRO: " + cliente.getEndereco().getBairro());
        System.out.println("ENDEREÇO CEP: " + cliente.getEndereco().getCep());
        System.out.println("ENDEREÇO COMPLEMENTO: " + cliente.getEndereco().getComplemento());
        System.out.println("ENDEREÇO NUMERO: " + cliente.getEndereco().getNumero());

        System.out.println("CIDADE ID: " + cliente.getEndereco().getCidade().getId());
        System.out.println("CIDADE NOME: " + cliente.getEndereco().getCidade().getNome());

        System.out.println("ESTADO ID: " + cliente.getEndereco().getCidade().getEstado().getId());
        System.out.println("ESTADO NOME: " + cliente.getEndereco().getCidade().getEstado().getNome());
        System.out.println("ESTADO UF: " + cliente.getEndereco().getCidade().getEstado().getUf());


//        String message = new ClienteBO().criar(cliente);
//        if (message.equals("OK")) {
//            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage("Usuário Cadastrado com sucesso"));
//        } else {
//            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(message));
//        }
    }

}
