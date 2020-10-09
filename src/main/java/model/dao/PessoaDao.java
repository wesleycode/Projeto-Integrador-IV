package model.dao;

import connections.ConnectionFactory;
import model.entities.Pessoa;

import javax.persistence.EntityManager;

public class PessoaDao extends GenericDao<Pessoa> {

    private EntityManager entityManager;

    public PessoaDao() {
        entityManager = new ConnectionFactory().getConnection();
    }

    public boolean isPessoaExisteNoBancoDeDados(Pessoa pessoa) throws Exception {
        try {
            return entityManager.createQuery(
                    "SELECT p FROM Pessoa p WHERE p.email = :email AND p.senha = :senha AND p.tipoUsuario = :tipo", Pessoa.class)
                    .setParameter("email", pessoa.getEmail())
                    .setParameter("senha", pessoa.getSenha())
                    .setParameter("tipo", pessoa.getTipoUsuario())
                    .getResultList().size() > 0;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public Pessoa getByName(String name) throws Exception {
        try {
            return (Pessoa) entityManager.createQuery("from Pessoa p where p.nome = :name")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}