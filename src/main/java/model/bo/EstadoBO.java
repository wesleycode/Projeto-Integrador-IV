package model.bo;

import model.dao.EstadoDao;
import model.entities.Estado;

import java.util.List;
import model.dao.GenericDao;
public class EstadoBO implements GenericBO<Estado>{

    private GenericDao<Estado> genericDAO;

    public EstadoBO() {

    }

    public Estado listarEstadoPorNome(String n) throws Exception {
        EstadoDao estadoDAO = new EstadoDao();
        return estadoDAO.listarEstadoPorNome(n);
    }

    @Override
    public boolean criar(Estado o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(Estado o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Estado.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(Estado o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;
    }

    @Override
    public List<Estado> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Estado.class);
    }

    @Override
    public Estado getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Estado.class,id);
    }

    @Override
    public boolean valida(Estado o) throws Exception {
        if(o.getNome().equals("")){
            throw new Exception("Nome Estado nulo");
        }
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0){
            throw new Exception("Id nulo");
        }
        return true;
    }

}