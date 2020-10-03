package model.bo;

import model.entities.InformacoesPeca;

import java.util.List;
import model.dao.GenericDao;
public class InformacoesPecaBO implements GenericBO<InformacoesPeca>{

    private GenericDao<InformacoesPeca> genericDAO;

    public InformacoesPecaBO() {

    }

    @Override
    public boolean criar(InformacoesPeca o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(InformacoesPeca o) throws Exception {
        if (validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(InformacoesPeca.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(InformacoesPeca o) throws Exception {
        if(valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;
    }

    @Override
    public List<InformacoesPeca> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(InformacoesPeca.class);
    }

    @Override
    public InformacoesPeca getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(InformacoesPeca.class,id);
    }

    @Override
    public boolean valida(InformacoesPeca o) throws Exception {
        if(o.getInfopeca().equals("")){
            throw new Exception("informações de peça nulo");
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