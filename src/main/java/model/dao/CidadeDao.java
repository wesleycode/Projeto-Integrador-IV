package model.dao;

import connections.ConnectionFactory;
import model.entities.Cidade;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.EntityManager;

public class CidadeDao extends GenericDao<Cidade>{
    private EntityManager entityManager;
    public CidadeDao(){
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