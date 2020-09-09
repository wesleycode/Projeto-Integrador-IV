package dao;

import model.Estado;

import javax.persistence.Query;

public class EstadoDao extends DaoGenerica<Estado> {

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