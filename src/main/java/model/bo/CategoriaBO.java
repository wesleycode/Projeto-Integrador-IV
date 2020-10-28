package model.bo;

import model.dao.CategoriaDao;
import model.entities.Categoria;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Produto;

public class CategoriaBO implements GenericBO<Categoria> {

    @Override
    public boolean criar(Categoria o) throws Exception {
        if (valida(o)) {
            return new GenericDao<Categoria>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Categoria o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<Categoria>().deletar(Categoria.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Categoria o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<Categoria> listarTodos() throws Exception {
        return new GenericDao<Categoria>().listarTodos(Categoria.class);
    }

    @Override
    public Categoria getById(long id) throws Exception {
        return new GenericDao<Categoria>().getById(Categoria.class, id);
    }

    @Override
    public boolean valida(Categoria o) throws Exception {
        if (o.getCategoria().equals("")) {
            throw new Exception("Nome inválido da Categoria");
        }
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

    public Categoria getCategoriasPorNome(String nome) throws Exception {
        return new CategoriaDao().getCategoriasPorNome(nome);
    }

}
