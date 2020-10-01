package model.bo;

import model.entities.Cidade;

import java.util.List;

public class CidadeBO implements GenericBO<Cidade>{

    private GenericDao<Cidade> genericDAO;

    public CidadeBO() {

    }

    @Override
    public boolean criar(Cidade o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Cidade o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Cidade.class, o.getId());
    }

    @Override
    public boolean alterar(Cidade o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Cidade> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Cidade.class);
    }

    @Override
    public Cidade getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Cidade.class,id);
    }

    @Override
    public boolean valida(Cidade o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}
