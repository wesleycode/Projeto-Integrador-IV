package model.bo;

import model.dao.ItensCarrinhoDao;
import model.entities.Carrinho;
import model.entities.ItensCarrinho;

import java.util.List;
import model.dao.GenericDao;
import model.entities.Produto;

public class ItensCarrinhoBO implements GenericBO<ItensCarrinho>{

    private GenericDao<ItensCarrinho> genericDAO;

    public ItensCarrinhoBO() {

    }

    public List<ItensCarrinho> listarItensCarrinhoDeProduto(Produto produto) throws Exception {
        ItensCarrinhoDao itensCarrinhoDao = new ItensCarrinhoDao();
        return itensCarrinhoDao.listarItensCarrinhoDeProduto(produto);

    }

    public List<ItensCarrinho> ListarItensCarrinhoDeCarrinho(Carrinho carrinho) throws Exception{
        ItensCarrinhoDao itensCarrinhoDao = new ItensCarrinhoDao();
        return itensCarrinhoDao.ListarItensCarrinhoDeCarrinho(carrinho);

    }

    @Override
    public boolean criar(ItensCarrinho o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(ItensCarrinho o) throws Exception {
        if (validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(ItensCarrinho.class, o.getId());}return false;
    }

    @Override
    public boolean alterar(ItensCarrinho o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;
    }

    @Override
    public List<ItensCarrinho> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(ItensCarrinho.class);
    }

    @Override
    public ItensCarrinho getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(ItensCarrinho.class,id);
    }

    @Override
    public boolean valida(ItensCarrinho o) throws Exception {
        if (o.getCarrinho().getId()<0){
            throw new Exception("Carrinho não encontrado");
        }else if(o.getProduto().getId()<0){
            throw new Exception("Produto não encontrado");
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