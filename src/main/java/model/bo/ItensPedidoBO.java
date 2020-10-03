package model.bo;
import model.dao.GenericDao;
import model.dao.ItensPedidoDao;
import model.entities.ItensPedido;
import model.entities.Produto;

import java.util.List;

public class ItensPedidoBO implements GenericBO<ItensPedido>{

    private GenericDao<ItensPedido> genericDAO;

    public ItensPedidoBO() {

    }

    public List<ItensPedido> listarItensPedidoDeProduto(Produto produto) throws Exception {
        ItensPedidoDao itensPedidoDao = new ItensPedidoDao();
        return itensPedidoDao.listarItensPedidoDeProduto(produto);
    }

    @Override
    public boolean criar(ItensPedido o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(ItensPedido o) throws Exception {
        if (validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(ItensPedido.class, o.getId());}return false;
    }

    @Override
    public boolean alterar(ItensPedido o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);}return false;
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
        if (o.getProduto().getId()<0){
            throw new Exception("Protudo nulo");
        }else if(o.getPedido().getId()<0){
            throw new Exception("Pedido nulo");
        }
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0){
            throw new Exception("Id nulo");
        }
        return true;
    }

}