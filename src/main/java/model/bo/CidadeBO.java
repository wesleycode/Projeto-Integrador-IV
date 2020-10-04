package model.bo;

import model.dao.CidadeDao;
import model.entities.Cidade;

import java.util.List;
import model.dao.GenericDao;
import model.entities.Cliente;
import model.entities.Estado;

public class CidadeBO implements GenericBO<Cidade>{

    private GenericDao<Cidade> genericDAO;

    public CidadeBO() {

    }

    public List<Cidade> listarCidadePorEstado(Estado estado) throws Exception {
        CidadeDao cidadeDao = new CidadeDao();
        return cidadeDao.listarCidadePorEstado(estado);
    }

    @Override
    public boolean criar(Cidade o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(Cidade o) throws Exception {
        if (validaId(o.getId())){
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Cidade.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(Cidade o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;
    }

    @Override
    public List<Cidade> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Cidade.class);
    }

    @Override
    public Cidade getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Cidade.class, id);
    }

    @Override
    public boolean valida(Cidade o) throws Exception {
        if (o.getNome().equals("")){
            throw new Exception("nome inválido");
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
