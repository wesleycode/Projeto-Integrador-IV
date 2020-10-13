package model.dao;

import connections.ConnectionFactory;
import model.entities.Categoria;

import java.util.List;
import javax.persistence.EntityManager;

public class CategoriaDao  extends GenericDao<Categoria>{
    private EntityManager entityManager;
    public CategoriaDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public Categoria getCategoriasPorNome(String nome) throws Exception {
        try {
            List<Categoria> categ = entityManager.createQuery("SELECT a FROM Categoria a where a.categoria = :nome")
                    .setParameter("nome",nome)
                    .getResultList();
            return categ.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
