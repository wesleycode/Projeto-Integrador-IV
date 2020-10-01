package model.bo;
import model.dao.GenericDao;
import model.entities.Loja;

import java.util.List;

public class LojaBO implements GenericBO<Loja>{

    private GenericDao<Loja> genericDAO;

    public LojaBO() {

    }

    @Override
    public boolean criar(Loja o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Loja o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Loja.class, o.getId());
    }

    @Override
    public boolean alterar(Loja o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Loja> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Loja.class);
    }

    @Override
    public Loja getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Loja.class,id);
    }

    @Override
    public boolean valida(Loja o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}