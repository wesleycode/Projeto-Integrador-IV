package model.bo;
import model.dao.GenericDao;
import model.entities.Loja;

import java.util.List;

public class LojaBO implements GenericBO<Loja>{

    private GenericDao<Loja> genericDAO;

    public LojaBO() {

    }

    @Override
    public boolean criar(Loja o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);}return false;
    }

    @Override
    public boolean deletar(Loja o) throws Exception {
        if (validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Loja.class, o.getId());}return false;
    }

    @Override
    public boolean alterar(Loja o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;
    }

    @Override
    public List<Loja> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Loja.class);
    }

    @Override
    public Loja getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Loja.class,id);
    }

    @Override
    public boolean valida(Loja o) throws Exception {
        if (o.getCnpj().equals("")){
            throw new Exception("CNPJ nulo");
        }else if(o.getNomeLoja().equals("")){
            throw new Exception("nome nulo");
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