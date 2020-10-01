package model.bo;

import model.dao.GenericDao;
import model.entities.Carrinho;
import java.util.List;

public class CarrinhoBO  implements GenericBO<Carrinho>{

    private GenericDao<Carrinho> genericDAO;

    public CarrinhoBO() {

    }

    @Override
    public boolean criar(Carrinho o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Carrinho o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Carrinho.class, o.getId());
    }

    @Override
    public boolean alterar(Carrinho o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
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
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}
