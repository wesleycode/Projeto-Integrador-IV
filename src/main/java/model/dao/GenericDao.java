package model.dao;

import connections.ConnectionFactory;
import model.entities.EntityBase;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericDao<T extends EntityBase> {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public GenericDao() {
        entityManager = new ConnectionFactory().getConnection();
    }

    public boolean salvarOuAlterar(T t) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao salvar objeto da classe [" + t.getClass().getName() + "]");
        } finally {
            entityManager.close();
        }
    }

    public boolean deletar(Class<T> type, Long id) throws Exception {
        try {
            T t = entityManager.find(type, id);
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao deletar o [" + type.getName() + "]");
        } finally {
            entityManager.close();
        }
    }

    public List<T> listarTodos(Class<T> type) throws Exception {
        try {
            CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(type);
            query.from(type);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception("Erro ao listar todos da classe [" + type.getName() + "]");
        } finally {
            entityManager.close();
        }
    }

    public T getById(Class<T> type, long id) throws Exception {
        try {
            return entityManager.find(type, id);
        } catch (Exception e) {
            throw new Exception("Erro ao pegar por id na classe [" + type.getName() + "]");
        } finally {
            entityManager.close();
        }
    }

}