package model.bo;

import model.dao.GenericDao;
import model.entities.Avaliacao;

import java.util.List;

public class AvaliacaoBO implements GenericBO<Avaliacao>{

    private GenericDao<Avaliacao> genericDAO;

    public AvaliacaoBO() {

    }

    @Override
    public boolean criar(Avaliacao o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Avaliacao o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Avaliacao.class, o.getId());
    }

    @Override
    public boolean alterar(Avaliacao o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Avaliacao> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Avaliacao.class);
    }

    @Override
    public Avaliacao getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Avaliacao.class,id);
    }

    @Override
    public boolean valida(Avaliacao o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}
