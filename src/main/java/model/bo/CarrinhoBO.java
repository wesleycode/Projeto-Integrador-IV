package model.bo;

import model.dao.GenericDao;
import model.entities.Carrinho;
import model.entities.ItemCarrinho;
import model.entities.Produto;

import java.util.List;

public class CarrinhoBO implements GenericBO<Carrinho> {

    public double valorTotalComDesconto(double desconto, List<ItemCarrinho> itensDoCarrinho) {
        try {
            if ((desconto >= 0) && (desconto <= 100)) {
                return ((100-desconto) * valorTotalDoCarrinho(itensDoCarrinho)) / 100;
            } else {
                throw new Exception("Desconto deve estar entre 0% e 100%");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public long getLastId() {
        return new GenericDao<Carrinho>().getLastId(Carrinho.class);
    }

    public double valorTotalComFrete(List<ItemCarrinho> itensDoCarrinho) { return valorTotalDoCarrinho(itensDoCarrinho) * 1.02; }

    public double valorTotalCredito(List<ItemCarrinho> itensDoCarrinho) { return valorTotalDoCarrinho(itensDoCarrinho) * 1.01; }

    public double valorTotalDebito(List<ItemCarrinho> itensDoCarrinho) { return valorTotalDoCarrinho(itensDoCarrinho) * 0.9997; }

    public double valorTotalDoCarrinho(List<ItemCarrinho> itensDoCarrinho) {
        double valor = 0;
        for (ItemCarrinho item : itensDoCarrinho) {
            if (item.getQuantidade() == 1) {
                valor += item.getValor();
            } else {
                valor += item.getValor() * item.getQuantidade();
            }
        }
        return valor;
    }

    public Boolean atualizarInformacaoValorTotalEQauntidade(Carrinho carrinho) throws Exception {
        try {
            carrinho.setValorTotal(0);
            carrinho.setQuantidade(0);
            for (ItemCarrinho itemCarrinho : new ItemCarrinhoBO().ListarItensCarrinhoDeCarrinho(carrinho)) {
                carrinho.setQuantidade(carrinho.getQuantidade() + itemCarrinho.getQuantidade());
                carrinho.setValorTotal(carrinho.getValorTotal() + itemCarrinho.getValor());
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
        if (o.getCliente().getId() < 1) {
            throw new Exception("Antes de comprar voçê precisa logar-se");
        }
        if (o.getQuantidade() > 30) {
            throw new Exception("Numero máximo de itens em um carrinho é 30!");
        }
        if (o.getValorTotal() <= 0) {
            throw new Exception("Nenhum item em seu carrinho de compras!");
        }
        if (o.getCliente() == null) {
            throw new Exception("Efetue o login antes de finalizar a compra!");
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
