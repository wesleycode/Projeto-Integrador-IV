package controller;

import model.dao.GenericDao;
import model.entities.Cliente;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;

@Named
@RequestScoped
public class UsuarioController implements Serializable {

    public void criarUsuario() {
        Cliente cliente = new Cliente();
        cliente.setCarrinho(null);
        cliente.setAtivo(true);
        cliente.setCpf("asd");
        cliente.setDataNascimento(Date.valueOf("2019-09-01"));
        cliente.setEmail("kako.araujo.24@gmail.com");
        cliente.setEndereco(null);
        cliente.setNome("Wesley");
        cliente.setSenha("123");
        cliente.setTelefone("3345-4406");

        System.out.println("Criou cliente");

        try {
            System.out.println("Entrou no try");
            new GenericDao<Cliente>().salvar(cliente);
            System.out.println("Passou do try");
        } catch (Exception e) {
            System.out.println("Entrou no catch");
            System.out.println(e.getMessage());
        }
        System.out.println("Finalizou");
    }

    public String irParaPagina2() {
        return "page2?faces-redirect=true";
    }

}
