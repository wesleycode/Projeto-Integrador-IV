package model.bo;

import model.dao.PessoaDao;
import model.entities.Pessoa;

import java.util.List;

import model.dao.GenericDao;

public class PessoaBO implements GenericBO<Pessoa> {

    @Override
    public boolean criar(Pessoa o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Pessoa o) throws Exception {
        if (valida(o)) {
            return new GenericDao<Pessoa>().deletar(Pessoa.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Pessoa o) throws Exception {
        if (valida(o)) {
            return new GenericDao<Pessoa>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<Pessoa> listarTodos() throws Exception {
        return new GenericDao<Pessoa>().listarTodos(Pessoa.class);
    }

    @Override
    public Pessoa getById(int id) throws Exception {
        return new GenericDao<Pessoa>().getById(Pessoa.class, id);
    }

    @Override
    public boolean valida(Pessoa o) throws Exception {
        if (o.getCpf().equals("")) {
            throw new Exception("CPF nulo");
        } else if (o.getEmail().equals("")) {
            throw new Exception("email nulo");
        } else if (o.getEndereco().getId() < 0) {
            throw new Exception("Endereço nulo");
        } else if (o.getNome().equals("")) {
            throw new Exception("nome nulo");
        } else if (o.getSenha().equals("")) {
            throw new Exception("Senha nulo");
        } else if (o.getTelefone().equals("")) {
            throw new Exception("Telefone nulo");
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
    public Pessoa getByEmailandsenha(String email, String senha) throws Exception {
     return new PessoaDao().getByEmailandsenha(email,senha);
    }

    public String logarPessoa(Pessoa pessoa) {
        try {
            if (new PessoaDao().isPessoaExisteNoBancoDeDados(pessoa)) {
                return "OK";
            } else {
                return "SOPA";
            }
        } catch (Exception e) {
            return "ERRO AO LOGAR: " + e.getMessage();
        }
    }

}
