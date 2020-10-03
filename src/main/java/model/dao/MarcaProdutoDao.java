package model.dao;

import connections.ConnectionFactory;
import model.entities.MarcaProduto;

import javax.persistence.EntityManager;
import java.util.List;

public class MarcaProdutoDao extends GenericDao<MarcaProduto> {
    private EntityManager entityManager;
    public MarcaProdutoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public MarcaProduto listarMarcaPorNome(String nome) throws Exception {
        try {
            List<MarcaProduto> marcaProdutos = entityManager.createQuery("SELECT a FROM MarcaProduto a where  a.nome = :nome")
                    .setParameter("nome",nome)
                    .getResultList();
            return marcaProdutos.get(0);
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
