package model.bo;

import model.dao.CidadeDao;
import model.entities.Cidade;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Estado;
import model.entities.Produto;

public class CidadeBO implements GenericBO<Cidade> {

    @Override
    public boolean criar(Cidade o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }
    public long getLastId() {
        return new GenericDao<Cidade>().getLastId(Cidade.class);
    }

    @Override
    public boolean deletar(Cidade o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<Cidade>().deletar(Cidade.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Cidade o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<Cidade> listarTodos() throws Exception {
        return new GenericDao<Cidade>().listarTodos(Cidade.class);
    }

    public List<Cidade> listarCidadePorEstado(Estado estado) throws Exception {
        return new CidadeDao().listarCidadesPorEstado(estado);
    }

    @Override
    public Cidade getById(long id) throws Exception {
        return new GenericDao<Cidade>().getById(Cidade.class, id);
    }

    @Override
    public boolean valida(Cidade o) throws Exception {
        if (o.getNome().equals("")) {
            throw new Exception("nome inválido");
        }
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

}
