package model.bo;

import model.dao.FormaPagamentoDao;
import model.entities.FormaPagamento;

import java.util.List;
import model.dao.GenericDao;
public class FormaPagamentoBO implements GenericBO<FormaPagamento>{

    private GenericDao<FormaPagamento> genericDAO;

    public FormaPagamentoBO() {

    }

    public FormaPagamento ListarFormaPagamentoPorNome(String nome) throws Exception {
        FormaPagamentoDao fpdao = new FormaPagamentoDao();
        return fpdao.ListarFormaPagamentoPorNome(nome);
    }

    @Override
    public boolean criar(FormaPagamento o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvar(o);
        }return false;
    }

    @Override
    public boolean deletar(FormaPagamento o) throws Exception {
        if (validaId(o.getId())) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(FormaPagamento.class, o.getId());
        }return false;
    }

    @Override
    public boolean alterar(FormaPagamento o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.alterar(o);
        }return false;
    }

    @Override
    public List<FormaPagamento> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(FormaPagamento.class);
    }

    @Override
    public FormaPagamento getById(int id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(FormaPagamento.class,id);
    }

    @Override
    public boolean valida(FormaPagamento o) throws Exception {
        if (o.getFormapagamento().equals("")){
            throw new Exception("Forma de Pagamento nulo");
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