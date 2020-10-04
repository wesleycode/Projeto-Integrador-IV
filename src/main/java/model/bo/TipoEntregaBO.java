package model.bo;

import model.entities.TipoEntrega;

import java.util.List;
import model.dao.GenericDao;

public class TipoEntregaBO implements GenericBO<TipoEntrega>{

    private GenericDao<TipoEntrega> genericDAO;

    public TipoEntregaBO() {

    }

    @Override
    public boolean criar(TipoEntrega o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(TipoEntrega o) throws Exception {
        if(validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(TipoEntrega.class, o.getId());}return false;
    }

    @Override
    public boolean alterar(TipoEntrega o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);}return false;
    }

    @Override
    public List<TipoEntrega> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(TipoEntrega.class);
    }

    @Override
    public TipoEntrega getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(TipoEntrega.class,id);
    }

    @Override
    public boolean valida(TipoEntrega o) throws Exception {
        if (o.getTipoentrega().equals("")){
            throw new Exception("Tipo Entrega nulo");
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