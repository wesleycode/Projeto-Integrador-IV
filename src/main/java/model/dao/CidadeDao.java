package model.dao;

import connections.ConnectionFactory;
import model.entities.Cidade;
import model.entities.Estado;

import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.EntityManager;

public class CidadeDao extends GenericDao<Cidade>{
    private EntityManager entityManager;
    public CidadeDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<Cidade> listarCidadePorEstado(Estado estado) throws Exception {
        try {
            return entityManager.createQuery("SELECT a FROM Estado a where a.uf = :estado")
                    .setParameter("estado",estado.getUf())
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}