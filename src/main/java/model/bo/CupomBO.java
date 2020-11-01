package model.bo;

import model.dao.CupomDao;
import model.dao.GenericDao;
import model.entities.Cupom;
import java.util.List;

public class CupomBO implements GenericBO<Cupom> {

    @Override
    public boolean criar(Cupom o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Cupom o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<Cupom>().deletar(Cupom.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Cupom o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;

    }

    @Override
    public List<Cupom> listarTodos() throws Exception {
        return new GenericDao<Cupom>().listarTodos(Cupom.class);
    }

    @Override
    public Cupom getById(long id) throws Exception {
        return new GenericDao<Cupom>().getById(Cupom.class, id);
    }

    @Override
    public boolean valida(Cupom o) throws Exception {
        if (o.getId() <= 0) {
            throw new Exception("Cupom não existe");
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

    public boolean getByName(String value) throws Exception {
        return new CupomDao().getByName(value);
    }

}
