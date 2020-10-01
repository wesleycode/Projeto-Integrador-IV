package model.dao;

import model.entities.Carrinho;

import java.util.List;
import javax.persistence.EntityManager;

public class CarrinhoDao extends GenericDao<Carrinho>{
    private EntityManager entityManager;

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