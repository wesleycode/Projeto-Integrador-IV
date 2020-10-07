package model.dao;

import connections.ConnectionFactory;
import model.entities.Cidade;
import model.entities.Estado;

import java.util.List;
import javax.persistence.EntityManager;

public class CidadeDao extends GenericDao<Cidade>{

    private EntityManager entityManager;

    public CidadeDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<Cidade> listarCidadesPorEstado(Estado estado) throws Exception {
        try {
            return entityManager.createQuery("SELECT c FROM Cidade c WHERE c.estado = :estado ORDER BY c.nome")
                    .setParameter("estado", estado).getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}