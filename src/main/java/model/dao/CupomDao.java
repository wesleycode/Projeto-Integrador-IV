package model.dao;

import connections.ConnectionFactory;
import model.entities.Cupom;
import javax.persistence.EntityManager;

public class CupomDao extends GenericDao<Cupom> {

    private final EntityManager entityManager;

    public CupomDao() {
        entityManager = new ConnectionFactory().getConnection();
    }

    public boolean getByName(String name) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT c FROM Cupom c WHERE c.cupom = '");
        stringBuilder.append(name);
        stringBuilder.append("'");

        try {
            return entityManager.createQuery(stringBuilder.toString())
                    .getResultList().size() > 0;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

}