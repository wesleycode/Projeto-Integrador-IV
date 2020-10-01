package model.bo;

import model.entities.Categoria;

import java.util.List;
import model.dao.GenericDao;
public class CategoriaBO implements GenericBO<Categoria>{

    private GenericDao<Categoria> genericDAO;

    public CategoriaBO() {

    }

    @Override
    public boolean criar(Categoria o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Categoria o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Categoria.class, o.getId());
    }

    @Override
    public boolean alterar(Categoria o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Categoria> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Categoria.class);
    }

    @Override
    public Categoria getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Categoria.class,id);
    }

    @Override
    public boolean valida(Categoria o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}
