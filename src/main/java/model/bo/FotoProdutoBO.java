package model.bo;

import model.dao.FotoProdutoDao;
import model.dao.GenericDao;
import model.entities.FotoProduto;
import model.entities.Produto;

import java.util.List;

public class FotoProdutoBO implements GenericBO<FotoProduto>{

    private GenericDao<FotoProduto> genericDAO;

    public FotoProdutoBO() {

    }

    public List<FotoProduto> listarFotoProdutoDeProduto(Produto produto) throws Exception {
        FotoProdutoDao fotoProdutoDao = new FotoProdutoDao();
        return fotoProdutoDao.listarFotoProdutoDeProduto(produto);
    }

    @Override
    public boolean criar(FotoProduto o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(FotoProduto o) throws Exception {
        if(validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(FotoProduto.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(FotoProduto o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);}return false;
    }

    @Override
    public List<FotoProduto> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(FotoProduto.class);
    }

    @Override
    public FotoProduto getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(FotoProduto.class,id);
    }

    @Override
    public boolean valida(FotoProduto o) throws Exception {
        if (o.getFoto().equals("")){
            throw new Exception("Link da foto nulo");
        }else if(o.getProduto().getId()<0){
            throw new Exception("Protudo da foto nulo");
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