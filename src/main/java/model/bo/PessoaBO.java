package model.bo;

import model.entities.Pessoa;

import java.util.List;
import model.dao.GenericDao;

public class PessoaBO implements GenericBO<Pessoa>{

   private GenericDao<Pessoa> genericDAO;

    public PessoaBO() {

    }

    @Override
    public boolean criar(Pessoa o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Pessoa o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Pessoa.class, o.getId());
    }

    @Override
    public boolean alterar(Pessoa o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Pessoa> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Pessoa.class);
    }

    @Override
    public Pessoa getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Pessoa.class,id);
    }

    @Override
    public boolean valida(Pessoa o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}
