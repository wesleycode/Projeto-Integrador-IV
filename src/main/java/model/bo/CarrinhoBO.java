package model.bo;

import model.dao.CarrinhoDao;
import model.dao.GenericDao;
import model.entities.Carrinho;
import model.entities.ItensCarrinho;

import java.util.List;

public class CarrinhoBO  implements GenericBO<Carrinho>{

    private GenericDao<Carrinho> genericDAO;

    public CarrinhoBO() {

    }
    public Boolean atualizarInformacaoValorTotalEQauntidade(Carrinho carrinho) throws Exception{
        try {
            carrinho.setValorTotal(0);
            carrinho.setQuantidade(0);
            for (ItensCarrinho itensCarrinho : new ItensCarrinhoBO().ListarItensCarrinhoDeCarrinho(carrinho)) {
                carrinho.setQuantidade(carrinho.getQuantidade() + itensCarrinho.getQuantidade());
                carrinho.setValorTotal(carrinho.getValorTotal() + itensCarrinho.getValor());
            }
            return alterar(carrinho);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean criar(Carrinho o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<Carrinho>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(Carrinho o) throws Exception {
        if (validaId(o.getId())){
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Carrinho.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(Carrinho o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;

    }

    @Override
    public List<Carrinho> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Carrinho.class);
    }

    @Override
    public Carrinho getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Carrinho.class,id);
    }

    @Override
    public boolean valida(Carrinho o) throws Exception {
        if(o.getCliente().equals(null)){
            throw new Exception("Carrinho sem usuário");
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
