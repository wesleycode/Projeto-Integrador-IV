package model.dao;

import connections.ConnectionFactory;
import model.entities.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoDao extends GenericDao<Endereco>{
    private EntityManager entityManager;
    public EnderecoDao(){
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
