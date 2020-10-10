package model.dao;

import connections.ConnectionFactory;
import model.entities.Estado;
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

    public List<Produto> listarProdutosEntre(int page, int pagSize) throws Exception {
        long start = (page-1)*pagSize;
        try {
            return getEntityManager().createQuery("SELECT p FROM Produto p where p.id >= :st order by p.nome")
                    .setMaxResults(pagSize)
                    .setParameter("st", start)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            getEntityManager().close();
        }
    }

}
