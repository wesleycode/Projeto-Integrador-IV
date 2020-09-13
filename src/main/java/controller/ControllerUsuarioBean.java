package controller;

import dao.UsuarioDao;
import model.Usuario;

import javax.inject.Named;

@Named
public class ControllerUsuarioBean {

    public void criarUsuario() {
        System.out.println("Aqui");
        try {
            new UsuarioDao().salvar(new Usuario());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
