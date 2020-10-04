package model.dao;

import connections.ConnectionFactory;
import model.entities.Categoria;
import model.entities.FormaPagamento;
import model.entities.Fornecedor;

import javax.persistence.EntityManager;
import java.util.List;

public class FormaPagamentoDao extends GenericDao<Fornecedor>{
    private EntityManager entityManager;
    public FormaPagamentoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }
    public FormaPagamento listarFormaPagamentoPorNome(String nome) throws Exception {
        try {
            List<FormaPagamento> formaPagamentos = entityManager.createQuery("SELECT a FROM FormaPagamento a where a.formapagamento = :nome")
                    .setParameter("nome",nome)
                    .getResultList();
            return formaPagamentos.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
