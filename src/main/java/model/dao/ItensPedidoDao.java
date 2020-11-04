package model.dao;

import connections.ConnectionFactory;
import model.entities.ItemPedido;
import model.entities.Produto;

import java.util.List;
import javax.persistence.EntityManager;

public class ItensPedidoDao extends GenericDao<ItemPedido>{
    private EntityManager entityManager;
    public ItensPedidoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<ItemPedido> listarItensPedidoDeProduto(Produto produto) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM ItemPedido a where a.produto =:produto")
                    .setParameter("produto",produto)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
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