package model.dao;

import connections.ConnectionFactory;
import model.entities.Avaliacao;
import model.entities.Pessoa;
import model.entities.Produto;

import java.util.List;
import javax.persistence.EntityManager;

public class AvaliacaoDao extends GenericDao<Avaliacao>{
    private EntityManager entityManager;
    public AvaliacaoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public Avaliacao listarAvaliaçaodeprodutoexiste(Pessoa p,Produto pr) throws Exception {
        try {
            List<Avaliacao>avaliacaos = entityManager.createQuery("SELECT a FROM Avaliacao a where a.pessoa = :pessoa and a.produto = :produto")
                    .setParameter("pessoa",p)
                    .setParameter("produto",pr)
                    .getResultList();
            System.out.println("9999999999999");
            if (avaliacaos.isEmpty()) {
                return new Avaliacao();
            }
            System.out.println("10000000000000");
            return avaliacaos.get(0);
        } catch (Exception e) {
            System.out.println("Deu ruim aqui!");
            throw new Exception(e.getMessage());
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
