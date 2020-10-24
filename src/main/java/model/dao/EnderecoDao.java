package model.dao;

import connections.ConnectionFactory;
import model.entities.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoDao extends GenericDao<Endereco>{
    private EntityManager entityManager;
    public EnderecoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public Endereco listarultimoendereco() throws Exception {
        try {
            List<Endereco> enderecos = entityManager.createQuery("SELECT a FROM Endereco a order by a.id desc ").setMaxResults(1).getResultList();
            return enderecos.get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
