package model.bo;

import model.entities.ItensCarrinho;

import java.util.List;
import model.dao.GenericDao;

public class ItensCarrinhoBO implements GenericBO<ItensCarrinho>{

    private GenericDao<ItensCarrinho> genericDAO;

    public ItensCarrinhoBO() {

    }

    @Override
    public boolean criar(ItensCarrinho o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(ItensCarrinho o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(ItensCarrinho.class, o.getId());
    }

    @Override
    public boolean alterar(ItensCarrinho o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
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
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}