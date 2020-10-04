package model.dao;

import connections.ConnectionFactory;
import model.entities.Peca;
import model.entities.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class PecaDao extends GenericDao<Peca>{
    private EntityManager entityManager;
    public PecaDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<Peca> listarProdutoscomPeca(Produto produto) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM Peca a where a.produto = :produto")
                    .setParameter("produto", produto)
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
