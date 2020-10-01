package model.bo;

import model.entities.MarcaProduto;

import java.util.List;

public class MarcaProdutoBO implements GenericBO<MarcaProduto>{

    private GenericDao<MarcaProduto> genericDAO;

    public MarcaProdutoBO() {

    }

    @Override
    public boolean criar(MarcaProduto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(MarcaProduto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(MarcaProduto.class, o.getId());
    }

    @Override
    public boolean alterar(MarcaProduto o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<MarcaProduto> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(MarcaProduto.class);
    }

    @Override
    public MarcaProduto getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(MarcaProduto.class,id);
    }

    @Override
    public boolean valida(MarcaProduto o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}