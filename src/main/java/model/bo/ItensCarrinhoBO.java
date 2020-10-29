package model.bo;

import model.dao.ItensCarrinhoDao;
import model.entities.Carrinho;
import model.entities.ItensCarrinho;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Produto;

public class ItensCarrinhoBO implements GenericBO<ItensCarrinho> {

    public List<ItensCarrinho> listarItensCarrinhoDeProduto(Produto produto) throws Exception {
        ItensCarrinhoDao itensCarrinhoDao = new ItensCarrinhoDao();
        return itensCarrinhoDao.listarItensCarrinhoDeProduto(produto);
    }

    public List<ItensCarrinho> ListarItensCarrinhoDeCarrinho(Carrinho carrinho) throws Exception {
        ItensCarrinhoDao itensCarrinhoDao = new ItensCarrinhoDao();
        return itensCarrinhoDao.ListarItensCarrinhoDeCarrinho(carrinho);

    }

    @Override
    public boolean criar(ItensCarrinho o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItensCarrinho>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(ItensCarrinho o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<ItensCarrinho>().deletar(ItensCarrinho.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(ItensCarrinho o) throws Exception {
        if (valida(o)) {
            return new GenericDao<ItensCarrinho>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<ItensCarrinho> listarTodos() throws Exception {
        return new GenericDao<ItensCarrinho>().listarTodos(ItensCarrinho.class);
    }

    @Override
    public ItensCarrinho getById(long id) throws Exception {
        return new GenericDao<ItensCarrinho>().getById(ItensCarrinho.class, id);
    }

    @Override
    public boolean valida(ItensCarrinho o) throws Exception {
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