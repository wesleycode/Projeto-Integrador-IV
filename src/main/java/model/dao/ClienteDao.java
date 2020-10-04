package model.dao;

import connections.ConnectionFactory;
import model.entities.Cliente;
import model.entities.Pessoa;

import java.util.List;
import javax.persistence.EntityManager;

public class ClienteDao extends GenericDao<Cliente> {

    private EntityManager entityManager;

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

}