package model.bo;

import model.entities.Pedido;

import java.util.List;

import model.dao.GenericDao;

public class PedidoBO implements GenericBO<Pedido> {

    private GenericDao<Pedido> genericDAO;

    public PedidoBO() {

    }

    @Override
    public boolean criar(Pedido o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Pedido o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Pedido.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Pedido o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }
        return false;
    }

    @Override
    public List<Pedido> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Pedido.class);
    }

    @Override
    public Pedido getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Pedido.class, id);
    }

    @Override
    public boolean valida(Pedido o) throws Exception {
        if (o.getPessoa().getId() < 0) {
            throw new Exception("cliente nulo");
        } else if (o.getQuantidade() < 0) {
            throw new Exception("quantidade nulo");
        } else if (o.getValorTotal() < 0) {
            throw new Exception("totalvalor nulo");
        } else if (o.getEndereco().getId() < 0) {
            throw new Exception("Endereço nulo");
        }

        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

}