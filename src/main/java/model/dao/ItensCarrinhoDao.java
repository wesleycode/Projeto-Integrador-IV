package model.dao;

import connections.ConnectionFactory;
import model.entities.ItensCarrinho;
import model.entities.ItensPedido;
import model.entities.Produto;

import java.util.List;
import javax.persistence.EntityManager;

public class ItensCarrinhoDao extends GenericDao<ItensCarrinho>{
    private EntityManager entityManager;
    public ItensCarrinhoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<ItensCarrinho> listarItensCarrinhoDeProduto(Produto produto) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM ItensCarrinho a where a.produto =:produto")
                    .setParameter("produto",produto)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
