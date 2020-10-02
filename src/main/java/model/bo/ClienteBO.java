package model.bo;

import model.dao.ClienteDao;
import model.entities.Cliente;

import java.util.List;
import model.dao.GenericDao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ClienteBO implements GenericBO<Cliente> {

    private GenericDao<Cliente> genericDAO;

    public ClienteBO() {

    }

    @Override
    public boolean criar(Cliente o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Cliente o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Cliente.class, o.getId());
    }

    @Override
    public boolean alterar(Cliente o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Cliente> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Cliente.class);
    }

    @Override
    public Cliente getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Cliente.class, id);
    }

    @Override
    public boolean valida(Cliente o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

    public String logar(Cliente cliente) {
        try {
            if (new ClienteDao().isClienteExisteNoBancoDeDados(cliente.getEmail(), cliente.getSenha())) {
                return "OK";
            } else {
                return "";
            }
        } catch (Exception e) {
            return "ERRO AO LOGAR: " + e.getMessage();
        }
    }

    public boolean validarCliente(String email, String senha) {
        System.out.println("EMAIL: " + email + " SENHA: " + senha + " VALIDADO COM ESSES VALORES!");
        return true;
    }

}