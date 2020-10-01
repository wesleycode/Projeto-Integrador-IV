package model.bo;
import model.dao.GenericDao;
import model.entities.ItensPedido;

import java.util.List;

public class ItensPedidoBO implements GenericBO<ItensPedido>{

    private GenericDao<ItensPedido> genericDAO;

    public ItensPedidoBO() {

    }

    @Override
    public boolean criar(ItensPedido o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(ItensPedido o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(ItensPedido.class, o.getId());
    }

    @Override
    public boolean alterar(ItensPedido o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<ItensPedido> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(ItensPedido.class);
    }

    @Override
    public ItensPedido getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(ItensPedido.class,id);
    }

    @Override
    public boolean valida(ItensPedido o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}