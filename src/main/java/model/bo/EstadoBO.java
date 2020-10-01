package model.bo;

import model.entities.Estado;

import java.util.List;
import model.dao.GenericDao;
public class EstadoBO implements GenericBO<Estado>{

    private GenericDao<Estado> genericDAO;

    public EstadoBO() {

    }

    @Override
    public boolean criar(Estado o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Estado o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Estado.class, o.getId());
    }

    @Override
    public boolean alterar(Estado o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Estado> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Estado.class);
    }

    @Override
    public Estado getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Estado.class,id);
    }

    @Override
    public boolean valida(Estado o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}