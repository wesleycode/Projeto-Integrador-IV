package model.bo;

import model.dao.PecaDao;
import model.entities.Peca;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Produto;

public class PecaBO implements GenericBO<Peca>{

    private GenericDao<Peca> genericDAO;

    public PecaBO() {

    }
    public List<Peca> listarProdutoscomPeca(Produto produto) throws Exception {
        PecaDao pecaDao = new PecaDao();
        return pecaDao.listarProdutoscomPeca(produto);
    }

    @Override
    public boolean criar(Peca o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(Peca o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Peca.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(Peca o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);}return false;
    }

    @Override
    public List<Peca> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Peca.class);
    }

    @Override
    public Peca getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Peca.class,id);
    }

    @Override
    public boolean valida(Peca o) throws Exception {
        if (o.getInfopeca().getId()<0){
            throw new Exception("Peça sem informação nulo");
        }else if(o.getPeca().equals("")){
            throw new Exception("peça nulo");
        }else if (o.getProduto().getId()<0){
            throw new Exception("Produto nulo");
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