package model.dao;

import connections.ConnectionFactory;
import model.entities.Loja;

import javax.persistence.EntityManager;
import java.util.List;

public class LojaDao extends GenericDao<Loja>{
    private EntityManager entityManager;
    public LojaDao(){
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
