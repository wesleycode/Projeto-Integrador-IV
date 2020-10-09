package controller;

import model.dao.GenericDao;
import model.entities.Pessoa;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;

@Named
@RequestScoped
public class UsuarioController implements Serializable {

    public void criarUsuario() {
        Pessoa pessoa = new Pessoa();
        //cliente.setCarrinho(new Carrinho());
        pessoa.setAtivo(true);
        pessoa.setCpf("asd");
        pessoa.setDataNascimento(Date.valueOf("2019-09-01"));
        pessoa.setEmail("kako.araujo.24@gmail.com");
        pessoa.setEndereco(null);
        pessoa.setNome("Wesley");
        pessoa.setSenha("123");
        pessoa.setTelefone("3345-4406");

        System.out.println("Criou cliente");

        try {
            System.out.println("Entrou no try");
            new GenericDao<Pessoa>().salvar(pessoa);
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
