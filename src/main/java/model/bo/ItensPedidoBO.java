package model.bo;

import model.dao.GenericDao;
import model.dao.ItensPedidoDao;
import model.entities.ItensPedido;
import model.entities.Produto;

import java.util.List;

public class ItensPedidoBO implements GenericBO<ItensPedido> {

    public List<ItensPedido> listarItensPedidoDeProduto(Produto produto) throws Exception {
        return new ItensPedidoDao().listarItensPedidoDeProduto(produto);
    }

    @Override
    public boolean criar(ItensPedido o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItensPedido>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(ItensPedido o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<ItensPedido>().deletar(ItensPedido.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(ItensPedido o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItensPedido>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<ItensPedido> listarTodos() throws Exception {
        return new GenericDao<ItensPedido>().listarTodos(ItensPedido.class);
    }

    @Override
    public ItensPedido getById(int id) throws Exception {
        return new GenericDao<ItensPedido>().getById(ItensPedido.class, id);
    }

    @Override
    public boolean valida(ItensPedido o) throws Exception {
        if (o.getProduto().getId() < 0) {
            throw new Exception("Protudo nulo");
        } else if (o.getPedido().getId() < 0) {
            throw new Exception("Pedido nulo");
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