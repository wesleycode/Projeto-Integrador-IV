package model.dao;

import model.entities.Peca;

import javax.persistence.EntityManager;
import java.util.List;

public class PecaDao extends GenericDao<Peca>{
    private EntityManager entityManager;


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
