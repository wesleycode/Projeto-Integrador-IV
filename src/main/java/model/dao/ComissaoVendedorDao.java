package model.dao;

import connections.ConnectionFactory;
import model.entities.ComissaoVendedor;
import model.entities.Fornecedor;
import model.entities.Pedido;
import model.entities.Pessoa;

import javax.persistence.EntityManager;
import java.util.List;

public class ComissaoVendedorDao extends GenericDao<Fornecedor>{
    private EntityManager entityManager;
    public ComissaoVendedorDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<ComissaoVendedor> listarComissaoPorPessoa(Pessoa pessoa) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM ComissaoVendedor a where a.pessoa = :pessoa")
                    .setParameter("pessoa",pessoa)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
