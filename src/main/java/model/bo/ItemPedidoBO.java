package model.bo;

import model.dao.GenericDao;
import model.dao.ItensPedidoDao;
import model.entities.ItemPedido;
import model.entities.Produto;

import java.util.List;

public class ItemPedidoBO implements GenericBO<ItemPedido> {

    public List<ItemPedido> listarItensPedidoDeProduto(Produto produto) throws Exception {
        return new ItensPedidoDao().listarItensPedidoDeProduto(produto);
    }

    @Override
    public boolean criar(ItemPedido o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItemPedido>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(ItemPedido o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<ItemPedido>().deletar(ItemPedido.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(ItemPedido o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItemPedido>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<ItemPedido> listarTodos() throws Exception {
        return new GenericDao<ItemPedido>().listarTodos(ItemPedido.class);
    }

    @Override
    public ItemPedido getById(long id) throws Exception {
        return new GenericDao<ItemPedido>().getById(ItemPedido.class, id);
    }

    @Override
    public boolean valida(ItemPedido o) throws Exception {
        if (o.getProduto().getId() < 0) {
            throw new Exception("Produto nulo");
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
    public long getLastId() {
        return new GenericDao<ItemPedido>().getLastId(ItemPedido.class);
    }

}