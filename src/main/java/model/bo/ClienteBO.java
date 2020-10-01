package model.bo;

import model.entities.Cliente;

import java.util.List;
import model.dao.GenericDao;

public class ClienteBO implements GenericBO<Cliente>{

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
        return genericDAO.getById(Cliente.class,id);
    }

    @Override
    public boolean valida(Cliente o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}