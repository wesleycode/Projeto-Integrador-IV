package model.bo;

import model.entities.Pedido;

import java.util.List;

public class PedidoBO implements GenericBO<Pedido>{

    private GenericDao<Pedido> genericDAO;

    public PedidoBO() {

    }

    @Override
    public boolean criar(Pedido o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Pedido o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Pedido.class, o.getId());
    }

    @Override
    public boolean alterar(Pedido o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Pedido> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Pedido.class);
    }

    @Override
    public Pedido getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Pedido.class,id);
    }

    @Override
    public boolean valida(Pedido o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}