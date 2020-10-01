package model.bo;

import model.entities.Fornecedor;

import java.util.List;

public class FornecedorBO implements GenericBO<Fornecedor>{

    private GenericDao<Fornecedor> genericDAO;

    public FornecedorBO() {

    }

    @Override
    public boolean criar(Fornecedor o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);
    }

    @Override
    public boolean deletar(Fornecedor o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Fornecedor.class, o.getId());
    }

    @Override
    public boolean alterar(Fornecedor o) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);
    }

    @Override
    public List<Fornecedor> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Fornecedor.class);
    }

    @Override
    public Fornecedor getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Fornecedor.class,id);
    }

    @Override
    public boolean valida(Fornecedor o) throws Exception {
        return false;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        return false;
    }

}