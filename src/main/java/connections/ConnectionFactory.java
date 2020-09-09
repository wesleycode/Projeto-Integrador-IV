package connections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnity");

    public EntityManager getConnection() {
        return entityManagerFactory.createEntityManager();
    }

    public static void stopConnection() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}