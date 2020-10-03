package model.dao;

import connections.ConnectionFactory;
import model.entities.InformacoesPeca;

import javax.persistence.EntityManager;

public class InformacoesPecaDao extends GenericDao<InformacoesPeca>{

    private EntityManager entityManager;
    public InformacoesPecaDao(){
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
