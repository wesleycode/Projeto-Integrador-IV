package model.bo;

import model.dao.ComissaoVendedorDao;
import model.dao.FornecedorDao;
import model.dao.GenericDao;
import model.entities.ComissaoVendedor;
import model.entities.Fornecedor;
import model.entities.Pessoa;

import java.util.List;

public class ComissaoVendedorBO implements GenericBO<ComissaoVendedor>{

    private GenericDao<ComissaoVendedor> genericDAO;

    public ComissaoVendedorBO() {

    }

    public long getLastId() {
        return new GenericDao<ComissaoVendedor>().getLastId(ComissaoVendedor.class);
    }

    public List<ComissaoVendedor> listarComissaoPorPessoa(Pessoa pessoa) throws Exception {
        return new ComissaoVendedorDao().listarComissaoPorPessoa(pessoa);
    }

    @Override
    public boolean criar(ComissaoVendedor o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvarOuAlterar(o);
        }return false;
    }

    @Override
    public boolean deletar(ComissaoVendedor o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(ComissaoVendedor.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(ComissaoVendedor o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvarOuAlterar(o);
        }return false;
    }

    @Override
    public List<ComissaoVendedor> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(ComissaoVendedor.class);
    }

    @Override
    public ComissaoVendedor getById(long id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(ComissaoVendedor.class,id);
    }

    @Override
    public boolean valida(ComissaoVendedor o) throws Exception {
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