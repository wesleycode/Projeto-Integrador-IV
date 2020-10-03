package model.bo;

import model.dao.MarcaProdutoDao;
import model.entities.MarcaProduto;

import java.util.List;
import model.dao.GenericDao;

public class MarcaProdutoBO implements GenericBO<MarcaProduto>{

    private GenericDao<MarcaProduto> genericDAO;

    public MarcaProdutoBO() {

    }

    public MarcaProduto listarMarcaPorNome(String nome) throws Exception {
        MarcaProdutoDao marcaProdutoDao = new MarcaProdutoDao();
        return marcaProdutoDao.listarMarcaPorNome(nome);
    }

    @Override
    public boolean criar(MarcaProduto o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.salvar(o);}return false;
    }

    @Override
    public boolean deletar(MarcaProduto o) throws Exception {
        if (validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(MarcaProduto.class, o.getId());}return false;
    }

    @Override
    public boolean alterar(MarcaProduto o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);}return false;
    }

    @Override
    public List<MarcaProduto> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(MarcaProduto.class);
    }

    @Override
    public MarcaProduto getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(MarcaProduto.class,id);
    }

    @Override
    public boolean valida(MarcaProduto o) throws Exception {
        if (o.getNome().equals("")){
            throw new Exception("nome marcaProduto nulo");
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