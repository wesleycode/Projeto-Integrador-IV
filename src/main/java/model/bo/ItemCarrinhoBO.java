package model.bo;

import model.dao.ItensCarrinhoDao;
import model.entities.Carrinho;
import model.entities.ItemCarrinho;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Produto;

public class ItemCarrinhoBO implements GenericBO<ItemCarrinho> {

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

    public long getLastId() {
        return new GenericDao<ItemCarrinho>().getLastId(ItemCarrinho.class);
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
            throw new Exception("Carrinho do item não encontrado");
        }
        if (o.getProduto().getId() < 0) {
            throw new Exception("Produto não encontrado!");
        }
        if (o.getQuantidade() <= 0) {
            throw new Exception("Selecione pelo menos um produto!");
        }
        if (o.getValor() <= 0) {
            throw new Exception("Valor do produto menor ou igual a zero!");
        }
        return true;
    }

    public boolean valida(List<ItemCarrinho> i) throws Exception {
        if (i.isEmpty()) {
            throw new Exception("Adicione pelo menos um produto no seu carrinho!");
        }
        for (ItemCarrinho item : i) {
            valida(item);
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

    public List<ItemCarrinho> listarItensCarrinhoDeProduto(Produto produto) throws Exception {
        return new ItensCarrinhoDao().listarItensCarrinhoDeProduto(produto);
    }

    public List<ItemCarrinho> ListarItensCarrinhoDeCarrinho(Carrinho carrinho) throws Exception {
        return new ItensCarrinhoDao().ListarItensCarrinhoDeCarrinho(carrinho);
    }

}