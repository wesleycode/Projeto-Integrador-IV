package model.bo;

import model.dao.ClienteDao;
import model.entities.Cliente;

import java.util.List;

import model.dao.GenericDao;

public class ClienteBO implements GenericBO<Cliente> {

    public ClienteBO() {

    }

    @Override
    public boolean criar(Cliente o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Cliente o) throws Exception {
        if (valida(o)) {
            return new GenericDao<Cliente>().deletar(Cliente.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Cliente o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().alterar(o);
        }
        return false;
    }

    @Override
    public List<Cliente> listarTodos() throws Exception {
        return new GenericDao<Cliente>().listarTodos(Cliente.class);
    }

    @Override
    public Cliente getById(int id) throws Exception {
        if (validaId(id)) {
            return new GenericDao<Cliente>().getById(Cliente.class, id);
        }
        return null;
    }

    public Cliente getByName(String name) throws Exception {
        return new ClienteDao().getByName(name);
    }

    @Override
    public boolean valida(Cliente o) throws Exception {
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

    public String logar(Cliente cliente) {
        try {
            if (new ClienteDao().isClienteExisteNoBancoDeDados(cliente)) {
                return "OK";
            } else {
                return "SOPA";
            }
        } catch (Exception e) {
            return "ERRO AO LOGAR: " + e.getMessage();
        }
    }

}
