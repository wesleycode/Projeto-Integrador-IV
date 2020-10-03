package model.bo;

import model.dao.CategoriaDao;
import model.entities.Categoria;

import java.util.List;
import model.dao.GenericDao;
public class CategoriaBO implements GenericBO<Categoria>{

    private GenericDao<Categoria> genericDAO;

    public CategoriaBO() {

    }

    @Override
    public boolean criar(Categoria o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Categoria o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Categoria.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Categoria o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }
        return false;
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
        if (o.getCategoria().equals("")){
            throw new Exception("Nome inválido da Categoria");
        }
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0){
            throw new Exception("Id nulo");
        }
        return true;
    }
    public Categoria listarACategoriaPorNome(String nome) throws Exception {
        CategoriaDao categoriadao = new CategoriaDao();
        return categoriadao.listarACategoriaPorNome(nome);
    }

    }
