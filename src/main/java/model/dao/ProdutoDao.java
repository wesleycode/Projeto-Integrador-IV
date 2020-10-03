package model.dao;

import connections.ConnectionFactory;
import model.entities.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao extends GenericDao<Produto> {

    private EntityManager entityManager;

    public ProdutoDao(){
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
