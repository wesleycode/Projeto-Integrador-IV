package model.bo;

import model.entities.InformacoesPeca;

import java.util.List;

public class InformacoesPecaBO implements GenericBO<InformacoesPeca>{

    private GenericDao<InformacoesPeca> genericDAO;

    public InformacoesPecaBO() {

    }

    @Override
    public boolean criar(InformacoesPeca o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(InformacoesPeca o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(InformacoesPeca.class, o.getId());
    }

    @Override
    public boolean alterar(InformacoesPeca o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<InformacoesPeca> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(InformacoesPeca.class);
    }

    @Override
    public InformacoesPeca getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(InformacoesPeca.class,id);
    }

    @Override
    public boolean valida(InformacoesPeca o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}