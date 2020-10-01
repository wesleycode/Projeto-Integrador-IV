package model.bo;

import model.entities.TipoEntrega;

import java.util.List;
import model.dao.GenericDao;

public class TipoEntregaBO implements GenericBO<TipoEntrega>{

    private GenericDao<TipoEntrega> genericDAO;

    public TipoEntregaBO() {

    }

    @Override
    public boolean criar(TipoEntrega o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(TipoEntrega o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(TipoEntrega.class, o.getId());
    }

    @Override
    public boolean alterar(TipoEntrega o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<TipoEntrega> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(TipoEntrega.class);
    }

    @Override
    public TipoEntrega getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(TipoEntrega.class,id);
    }

    @Override
    public boolean valida(TipoEntrega o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}