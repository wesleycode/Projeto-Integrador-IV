package model.dao;

import connections.ConnectionFactory;
import model.entities.Avaliacao;
import model.entities.Produto;

import java.util.List;
import javax.persistence.EntityManager;

public class AvaliacaoDao extends GenericDao<Avaliacao>{
    private EntityManager entityManager;
    public AvaliacaoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<Avaliacao> listarAvaliacaoPorNota() throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM Avaliacao a order by a.nota asc ").getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
    public List<Avaliacao> listarAvaliacaoPorProduto(Produto produto) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM Avaliacao a where a.produto = :produto")
                    .setParameter("produto",produto)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
    public boolean isAvaliacaoExisteNoBancoDeDados(Avaliacao avaliacaofeita) throws Exception {
        try {
            return entityManager.createQuery(
                    "SELECT c FROM Avaliacao c where c.pessoa = :pessoa and c.produto = :produto", Avaliacao.class)
                    .setParameter("pessoa", avaliacaofeita.getPessoa())
                    .setParameter("produto", avaliacaofeita.getProduto())
                    .getResultList().size() > 0;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
