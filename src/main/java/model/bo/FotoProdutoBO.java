package model.bo;

import model.dao.GenericDao;
import model.dao.MarcaProdutoDao;
import model.entities.FotoProduto;
import model.entities.MarcaProduto;

import java.util.List;

public class FotoProdutoBO implements GenericBO<FotoProduto> {

    public MarcaProduto listarMarcaPorNome(String nome) throws Exception {
        return new MarcaProdutoDao().listarMarcaPorNome(nome);
    }

    @Override
    public boolean criar(FotoProduto o) throws Exception {
        if (valida(o)) {
            return new GenericDao<FotoProduto>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(FotoProduto o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<FotoProduto>().deletar(FotoProduto.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(FotoProduto o) throws Exception {
        if (valida(o)) {
            return new GenericDao<FotoProduto>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<FotoProduto> listarTodos() throws Exception {
        return new GenericDao<FotoProduto>().listarTodos(FotoProduto.class);
    }

    @Override
    public FotoProduto getById(int id) throws Exception {
        return new GenericDao<FotoProduto>().getById(FotoProduto.class, id);
    }

    @Override
    public boolean valida(FotoProduto o) throws Exception {
        if (o.getFoto().equals("")) {
            throw new Exception("Link da foto nulo");
        }
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

}