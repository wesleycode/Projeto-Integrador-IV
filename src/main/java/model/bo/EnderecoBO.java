package model.bo;

import model.dao.EnderecoDao;
import model.entities.Endereco;

import java.util.List;

import model.dao.GenericDao;

public class EnderecoBO implements GenericBO<Endereco> {

    public EnderecoBO() {

    }

    @Override
    public boolean criar(Endereco o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Endereco o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<Endereco>().deletar(Endereco.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Endereco o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<Endereco> listarTodos() throws Exception {
        return new GenericDao<Endereco>().listarTodos(Endereco.class);
    }

    @Override
    public Endereco getById(long id) throws Exception {
        return new GenericDao<Endereco>().getById(Endereco.class, id);
    }

    @Override
    public boolean valida(Endereco o) throws Exception {
        if (o.getBairro().equals("")) {
            throw new Exception("Bairro nulo");
        } else if (o.getCep().equals("")) {
            throw new Exception("CEP nulo");
        } else if (o.getCidade().getId() < 0) {
            throw new Exception("Cidade nulo");
        } else if (o.getNumero() < 0) {
            throw new Exception("Numero nulo");
        } else if (o.getRua().equals("")) {
            throw new Exception("Rua nulo");
        } else if (o.getBairro().equals("")) {
            throw new Exception("Bairro nulo");
        }
        return true;
    }
    public Endereco listarultimoendereco() throws Exception {
        return new EnderecoDao().listarultimoendereco();
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id menor que zero");
        }
        return true;
    }

}