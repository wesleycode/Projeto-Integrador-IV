package model.bo;

import model.dao.GenericDao;
import model.entities.Carrinho;
import model.entities.ItensCarrinho;

import java.util.List;

public class CarrinhoBO implements GenericBO<Carrinho> {

    public Boolean atualizarInformacaoValorTotalEQauntidade(Carrinho carrinho) throws Exception {
        try {
            carrinho.setValorTotal(0);
            carrinho.setQuantidade(0);
            for (ItensCarrinho itensCarrinho : new ItensCarrinhoBO().ListarItensCarrinhoDeCarrinho(carrinho)) {
                carrinho.setQuantidade(carrinho.getQuantidade() + itensCarrinho.getQuantidade());
                carrinho.setValorTotal(carrinho.getValorTotal() + itensCarrinho.getValor());
            }
            return alterar(carrinho);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean criar(Carrinho o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Carrinho o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<Carrinho>().deletar(Carrinho.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Carrinho o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;

    }

    @Override
    public List<Carrinho> listarTodos() throws Exception {
        return new GenericDao<Carrinho>().listarTodos(Carrinho.class);
    }

    @Override
    public Carrinho getById(long id) throws Exception {
        return new GenericDao<Carrinho>().getById(Carrinho.class, id);
    }

    @Override
    public boolean valida(Carrinho o) throws Exception {
        if (o.getCliente().equals(null)) {
            throw new Exception("Carrinho sem usuário");
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
