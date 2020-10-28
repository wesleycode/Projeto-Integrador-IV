package model.bo;

import model.dao.FornecedorDao;
import model.entities.Fornecedor;

import java.util.List;
import model.dao.GenericDao;
public class FornecedorBO implements GenericBO<Fornecedor>{

    private GenericDao<Fornecedor> genericDAO;

    public FornecedorBO() {

    }

    public Fornecedor listarFornecedorPorNome(String nome) throws Exception {
        FornecedorDao fornecedorDao = new FornecedorDao();
        return fornecedorDao.listarFornecedorPorNome(nome);

    }

    @Override
    public boolean criar(Fornecedor o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvarOuAlterar(o);
        }return false;
    }

    @Override
    public boolean deletar(Fornecedor o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Fornecedor.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(Fornecedor o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvarOuAlterar(o);
        }return false;
    }

    @Override
    public List<Fornecedor> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Fornecedor.class);
    }

    @Override
    public Fornecedor getById(long id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Fornecedor.class,id);
    }

    @Override
    public boolean valida(Fornecedor o) throws Exception {
        if(o.getFornecedor().equals("")){
            throw new Exception("Fornecedor nulo");
        }else if(o.getHyperlink().equals("")){
            throw new Exception("Referencia ao fornecedor nulo");
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