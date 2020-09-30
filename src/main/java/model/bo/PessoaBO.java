package model.entities.bo;

import model.dao.GenericDao;
import model.entities.Pessoa;

import java.util.List;

public class PessoaBO implements GenericBO<Pessoa> {

    private GenericDao<Pessoa> genericDAO;

    public PessoaBO() {

    }

    @Override
    public boolean criar(Pessoa o) throws Exception {
        if (validar(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Pessoa o) throws Exception {
        if (validar(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Pessoa.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Pessoa o) throws Exception {
        if (validar(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }
        return false;
    }

    @Override
    public List<Pessoa> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Pessoa.class);
    }

    @Override
    public Pessoa getById(long id) throws Exception {
        if (validaId(id)) {
            genericDAO = new GenericDao<>();
            return genericDAO.getById(Pessoa.class, id);
        } else {
            return null;
        }
    }

    @Override
    public boolean validar(Pessoa o) throws Exception {
        validaId(o.getId());
        if (o.getNome().isEmpty()) {
            throw new Exception("Erro: NOME EM BRANCO");
        }
        if (o.getEmail().isEmpty()) {
            throw new Exception("Erro: EMAIL EM BRANCO");
        }
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Erro: ID MENOR QUE ZERO");
        }
        return true;
    }

}
