package model.bo;

import model.entities.Produto;

import java.util.List;

public class ProdutoBO implements GenericBO<Produto>{

    private GenericDao<Produto> genericDAO;

    public ProdutoBO() {

    }

    @Override
    public boolean criar(Produto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Produto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Produto.class, o.getId());
    }

    @Override
    public boolean alterar(Produto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Produto> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Produto.class);
    }

    @Override
    public Produto getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Produto.class,id);
    }

    @Override
    public boolean valida(Produto o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}