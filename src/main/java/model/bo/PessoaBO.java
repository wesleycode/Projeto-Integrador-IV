package model.bo;

import model.entities.Pessoa;

import java.util.List;
import model.dao.GenericDao;

public class PessoaBO implements GenericBO<Pessoa>{

   private GenericDao<Pessoa> genericDAO;

    public PessoaBO() {

    }

    @Override
    public boolean criar(Pessoa o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(Pessoa o) throws Exception {
        if (validaId(o.getId())){
        genericDAO = new GenericDao<>();
        return genericDAO.deletar(Pessoa.class, o.getId());}return false;
    }

    @Override
    public boolean alterar(Pessoa o) throws Exception {
        if (valida(o)){
        genericDAO = new GenericDao<>();
        return genericDAO.alterar(o);}return false;
    }

    @Override
    public List<Pessoa> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Pessoa.class);
    }

    @Override
    public Pessoa getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Pessoa.class,id);
    }

    @Override
    public boolean valida(Pessoa o) throws Exception {
        if (o.getCpf().equals("")){
            throw new Exception("CPF nulo");
        }else if(o.getEmail().equals("")){
            throw new Exception("email nulo");
        }else if (o.getEndereco().getId()<0){
            throw new Exception("Endereço nulo");
        }else if (o.getNome().equals("")){
            throw new Exception("nome nulo");
        }else if (o.getSenha().equals("")){
            throw new Exception("Senha nulo");
        }else if (o.getTelefone().equals("")){
            throw new Exception("Telefone nulo");
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
