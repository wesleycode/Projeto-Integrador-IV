
package model.dao;

import model.entities.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstadoDao extends GenericDao<Estado> {
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
    public Estado listarEstadoPorNome(String nome) throws Exception {
        try {
            Query query = getEntityManager().createQuery("SELECT e FROM Estado e where e.nome = :nome")
                    .setParameter("nome", nome);
            return (Estado) query.getResultList().get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            getEntityManager().close();
        }
    }

}