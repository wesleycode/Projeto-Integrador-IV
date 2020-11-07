package model.bo;

import model.dao.FormaPagamentoDao;
import model.entities.FormaPagamento;

import java.util.List;

import model.dao.GenericDao;
import model.entities.Produto;

public class FormaPagamentoBO implements GenericBO<FormaPagamento> {

    public FormaPagamento ListarFormaPagamentoPorNome(String nome) throws Exception {
        return new FormaPagamentoDao().listarFormaPagamentoPorNome(nome);
    }

    @Override
    public boolean criar(FormaPagamento o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }
    public long getLastId() {
        return new GenericDao<FormaPagamento>().getLastId(FormaPagamento.class);
    }


    @Override
    public boolean deletar(FormaPagamento o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<FormaPagamento>().deletar(FormaPagamento.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(FormaPagamento o) throws Exception {
        if (valida(o)) {
            return new GenericDao<>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<FormaPagamento> listarTodos() throws Exception {
        return new GenericDao<FormaPagamento>().listarTodos(FormaPagamento.class);
    }

    @Override
    public FormaPagamento getById(long id) throws Exception {
        return new GenericDao<FormaPagamento>().getById(FormaPagamento.class, id);
    }

    @Override
    public boolean valida(FormaPagamento o) throws Exception {
        if (o == null) {
            throw new Exception("Forma de Pagamento nula");
        }
        if (o.getId() == 0) {
            throw new Exception("Selecione pelo menos uma forma de pagamento!");
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