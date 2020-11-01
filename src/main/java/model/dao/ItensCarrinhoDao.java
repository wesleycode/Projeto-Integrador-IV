package model.dao;

import connections.ConnectionFactory;
import model.entities.Carrinho;
import model.entities.ItemCarrinho;
import model.entities.Produto;

import java.util.List;
import javax.persistence.EntityManager;

public class ItensCarrinhoDao extends GenericDao<ItemCarrinho>{
    private EntityManager entityManager;
    public ItensCarrinhoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<ItemCarrinho> listarItensCarrinhoDeProduto(Produto produto) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM ItemCarrinho a where a.produto =:produto")
                    .setParameter("produto",produto)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
    public List<ItemCarrinho> ListarItensCarrinhoDeCarrinho(Carrinho carrinho) throws Exception{
        try {
            return entityManager.createQuery("SELECT a FROM ItemCarrinho a where a.Carrinho =:car")
                    .setParameter("car",carrinho)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
