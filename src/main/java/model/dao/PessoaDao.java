package model.dao;

import connections.ConnectionFactory;
import model.entities.Pessoa;

import javax.persistence.EntityManager;
import java.util.List;

public class PessoaDao extends GenericDao<Pessoa> {

    private EntityManager entityManager;

    public PessoaDao() {
        entityManager = new ConnectionFactory().getConnection();
    }

    public boolean isPessoaExisteNoBancoDeDados(Pessoa pessoa) throws Exception {
        try {
            return entityManager.createQuery(
                    "SELECT p FROM Pessoa p WHERE p.email = :email AND p.senha = :senha AND p.tipoUsuario >= :tipo AND p.ativo = true", Pessoa.class)
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
    public List<Pessoa> listarPessoasPorNome() throws Exception {
        try {
            return entityManager.createQuery(
                    "SELECT p FROM Pessoa p order by p.nome")
                    .getResultList();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
    public List<Pessoa> listarPessoasPorNomeEspecifico(String nome) throws Exception {
        try {
            nome = "SELECT p FROM Pessoa p where p.nome like '%"+nome+"%'";
            return entityManager.createQuery(
                    nome)
                    .getResultList();
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

    public Pessoa getByEmailandsenha(String email, String senha) throws Exception {
        try {
            return (Pessoa) entityManager.createQuery("from Pessoa p where p.email = :email AND p.senha = :senha AND p.ativo = true")
                    .setParameter("email", email)
                    .setParameter("senha",senha).getSingleResult();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}