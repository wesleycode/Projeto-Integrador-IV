package controller;

import dao.ClienteDao;
import model.Cliente;

import javax.inject.Named;

@Named
public class ControllerUsuarioBean {

    public void criarUsuario() {
        System.out.println("Aqui");
        try {
            new ClienteDao().salvar(new Cliente());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String irParaPagina2() {
        return "page2?faces-redirect=true";
    }

}
