package model.bo;

import model.entities.FotoProduto;

import java.util.List;

public class FotoProdutoBO implements GenericBO<FotoProduto>{

    private GenericDao<FotoProduto> genericDAO;

    public FotoProdutoBO() {

    }

    @Override
    public boolean criar(FotoProduto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(FotoProduto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(FotoProduto.class, o.getId());
    }

    @Override
    public boolean alterar(FotoProduto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<FotoProduto> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(FotoProduto.class);
    }

    @Override
    public FotoProduto getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(FotoProduto.class,id);
    }

    @Override
    public boolean valida(FotoProduto o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}