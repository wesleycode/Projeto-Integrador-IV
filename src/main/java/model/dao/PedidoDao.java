package model.dao;

import connections.ConnectionFactory;
import model.entities.Pedido;
import model.entities.Pessoa;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDao extends GenericDao<Pedido> {

    private EntityManager entityManager;

    public PedidoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<Pedido> listarpedidosDaPessoa(Pessoa pessoa) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM Pedido a where a.pessoa = :pessoa")
                    .setParameter("pessoa",pessoa)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }


}
