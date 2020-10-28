package model.bo;

import model.entities.MarcaProduto;
import java.util.List;
import model.dao.GenericDao;

public class MarcaProdutoBO implements GenericBO<MarcaProduto> {

    @Override
    public boolean criar(MarcaProduto o) throws Exception {
        if (valida(o)) {
            return new GenericDao<MarcaProduto>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(MarcaProduto o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<MarcaProduto>().deletar(MarcaProduto.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(MarcaProduto o) throws Exception {
        if (valida(o)) {
            return new GenericDao<MarcaProduto>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<MarcaProduto> listarTodos() throws Exception {
        return new GenericDao<MarcaProduto>().listarTodos(MarcaProduto.class);
    }

    @Override
    public MarcaProduto getById(long id) throws Exception {
        return new GenericDao<MarcaProduto>().getById(MarcaProduto.class, id);
    }

    @Override
    public boolean valida(MarcaProduto o) throws Exception {
        if (o.getNome().equals("")) {
            throw new Exception("nome marcaProduto nulo");
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