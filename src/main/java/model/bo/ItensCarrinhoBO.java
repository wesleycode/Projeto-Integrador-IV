package model.bo;

import model.dao.ItensCarrinhoDao;
import model.entities.Carrinho;
import model.entities.ItemCarrinho;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Produto;

public class ItensCarrinhoBO implements GenericBO<ItemCarrinho> {

    public List<ItemCarrinho> listarItensCarrinhoDeProduto(Produto produto) throws Exception {
        ItensCarrinhoDao itensCarrinhoDao = new ItensCarrinhoDao();
        return itensCarrinhoDao.listarItensCarrinhoDeProduto(produto);
    }

    public List<ItemCarrinho> ListarItensCarrinhoDeCarrinho(Carrinho carrinho) throws Exception {
        ItensCarrinhoDao itensCarrinhoDao = new ItensCarrinhoDao();
        return itensCarrinhoDao.ListarItensCarrinhoDeCarrinho(carrinho);

    }

    @Override
    public boolean criar(ItemCarrinho o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItemCarrinho>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(ItemCarrinho o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<ItemCarrinho>().deletar(ItemCarrinho.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(ItemCarrinho o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItemCarrinho>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<ItemCarrinho> listarTodos() throws Exception {
        return new GenericDao<ItemCarrinho>().listarTodos(ItemCarrinho.class);
    }

    @Override
    public ItemCarrinho getById(long id) throws Exception {
        return new GenericDao<ItemCarrinho>().getById(ItemCarrinho.class, id);
    }

    @Override
    public boolean valida(ItemCarrinho o) throws Exception {
        if (o.getCarrinho().getId() < 0) {
            throw new Exception("Carrinho não encontrado");
        }
        if (o.getQuantidade() <= 0) {
            throw new Exception("Selecione pelo menos um produto em sua quantidade");
        }
        if (o.getProduto().getId() < 0) {
            throw new Exception("Produto não encontrado");
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