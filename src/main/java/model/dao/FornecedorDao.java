package model.dao;

import connections.ConnectionFactory;
import model.entities.FormaPagamento;
import model.entities.Fornecedor;

import javax.persistence.EntityManager;
import java.util.List;

public class FornecedorDao extends GenericDao<Fornecedor>{
    private EntityManager entityManager;
    public FornecedorDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public Fornecedor listarFornecedorPorNome(String nome) throws Exception {
        try {
            List<Fornecedor> fornecedors = entityManager.createQuery("SELECT a FROM Fornecedor a where a.Fornecedor = :nome")
                    .setParameter("nome",nome)
                    .getResultList();
            return fornecedors.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
