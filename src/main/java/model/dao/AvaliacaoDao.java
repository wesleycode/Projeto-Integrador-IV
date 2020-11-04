package model.dao;

import connections.ConnectionFactory;
import model.entities.Avaliacao;
import model.entities.Pessoa;
import model.entities.Produto;

import java.util.List;
import javax.persistence.EntityManager;

public class AvaliacaoDao extends GenericDao<Avaliacao> {

    private EntityManager entityManager;

    public AvaliacaoDao() {
        entityManager = new ConnectionFactory().getConnection();
    }

    public Avaliacao isExisteAvaliacaoDesteProduto(Pessoa p, Produto pr) throws Exception {
        try {
            return (Avaliacao) entityManager.createQuery("SELECT a FROM Avaliacao a WHERE a.pessoa = :pessoa AND a.produto = :produto")
                    .setParameter("pessoa", p)
                    .setParameter("produto", pr)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("RETORNOU NULL");
            return null;
        } finally {
            entityManager.close();
        }
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
                    .setParameter("produto", produto)
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

    public long getIdFromObject(Avaliacao avaliacao) throws Exception {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT c FROM Avaliacao c WHERE ");
            stringBuilder.append("c.comentario = :comentario ");
            stringBuilder.append("AND c.dataPostado = :datapostado ");
            stringBuilder.append("AND c.nota = :nota ");
            stringBuilder.append("AND c.pessoa = :pessoa ");
            stringBuilder.append("AND c.produto = :produto ");

            return entityManager.createQuery(stringBuilder.toString(), Avaliacao.class)
                    .setParameter("comentario",avaliacao.getComentario())
                    .setParameter("datapostado",avaliacao.getDataPostado())
                    .setParameter("nota",avaliacao.getNota())
                    .setParameter("pessoa", avaliacao.getPessoa())
                    .setParameter("produto", avaliacao.getProduto())
                    .getSingleResult().getId();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
