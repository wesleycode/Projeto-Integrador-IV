package model.bo;

import model.entities.Endereco;

import java.util.List;

import model.dao.GenericDao;
public class EnderecoBO implements GenericBO<Endereco>{

    private GenericDao<Endereco> genericDAO;

    public EnderecoBO() {

    }

    @Override
    public boolean criar(Endereco o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Endereco o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Endereco.class, o.getId());
    }

    @Override
    public boolean alterar(Endereco o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Endereco> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Endereco.class);
    }

    @Override
    public Endereco getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Endereco.class,id);
    }

    @Override
    public boolean valida(Endereco o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}