package model.bo;

import model.entities.Peca;

import java.util.List;

public class PecaBO implements GenericBO<Peca>{

    private GenericDao<Peca> genericDAO;

    public PecaBO() {

    }

    @Override
    public boolean criar(Peca o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Peca o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Peca.class, o.getId());
    }

    @Override
    public boolean alterar(Peca o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Peca> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Peca.class);
    }

    @Override
    public Peca getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Peca.class,id);
    }

    @Override
    public boolean valida(Peca o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}