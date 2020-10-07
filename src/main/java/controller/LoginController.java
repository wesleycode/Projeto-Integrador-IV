package controller;

import model.bo.*;
import model.entities.Carrinho;
import model.entities.Cidade;
import model.entities.Cliente;
import model.entities.Estado;
import net.bootsfaces.utils.FacesMessages;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private Cliente cliente;

    // Construtor //
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
            FacesMessages.info("Logado com sucesso", "detail message");
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesMessages.info("Usuário e/ou senha inválidos", "detail message");
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
        return irParaIndex();
    }

    public String irParaIndex() {
        return "/index.xhtml?faces-redirect=true";
    }

    public void cadastrarNovoCliente() {

        // Sempre ao CADASTRAR um novo cliente o status dele é ativo por padrão //
        cliente.setAtivo(true);

        // Grava o endereco //

        try {
            if (new EnderecoBO().criar(cliente.getEndereco())) {
                FacesMessages.info("Endereco cadastrado com sucesso");
            } else {
                FacesMessages.error("Erro ao cadastrar endereço do usuario");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }

        // Grava o cliente //

        try {
            if (new ClienteBO().criar(cliente)) {
                FacesMessages.info("Usuário Cadastrado com sucesso");
            } else {
                FacesMessages.error("Erro ao cadastrar usuário");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }

    }

}
