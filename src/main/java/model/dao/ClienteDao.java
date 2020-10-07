package model.dao;

import connections.ConnectionFactory;
import model.entities.Cliente;
import model.entities.Pessoa;

import java.util.List;
import javax.persistence.EntityManager;

public class ClienteDao extends GenericDao<Cliente> {

    private EntityManager entityManager;

    public ClienteDao() {
        entityManager = new ConnectionFactory().getConnection();
    }

    public boolean isClienteExisteNoBancoDeDados(Cliente cliente) throws Exception {
        try {
            return entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha", Cliente.class)
                    .setParameter("email", cliente.getEmail())
                    .setParameter("senha", cliente.getSenha())
                    .getResultList().size() > 0;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public Cliente getByName(String name) throws Exception {
        try {
            return (Cliente) entityManager.createQuery("from Cliente c where c.nome = :name")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}