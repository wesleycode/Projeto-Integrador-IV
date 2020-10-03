package model.dao;

import connections.ConnectionFactory;
import model.entities.FotoProduto;
import model.entities.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class FotoProdutoDao extends GenericDao<FotoProduto>{

    private EntityManager entityManager;
    public FotoProdutoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }
    public List<FotoProduto> listarFotoProdutoDeProduto(Produto produto) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM FotoProduto a where a.produto = :produto")
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
