package model.dao;

import connections.ConnectionFactory;
import model.entities.Pedido;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDao extends GenericDao<Pedido> {

    private EntityManager entityManager;

    public PedidoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }
    /*
    public List<Avaliacao> listarAvaliacaoPorNota() throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM Avaliacao a order by a.nota asc ").getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
     */

}
