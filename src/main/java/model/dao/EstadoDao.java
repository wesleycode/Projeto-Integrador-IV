package model.dao;

import connections.ConnectionFactory;
import model.entities.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstadoDao extends GenericDao<Estado> {
    private EntityManager entityManager;
    public EstadoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }


    public Estado listarEstadoPorNome(String nome) throws Exception {
        try {
            Query query = getEntityManager().createQuery("SELECT e FROM Estado e where e.nome = :nome")
                    .setParameter("nome", nome);
            return (Estado) query.getResultList().get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            getEntityManager().close();
        }
    }

}