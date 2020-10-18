package model.dao;

import connections.ConnectionFactory;
import model.entities.Categoria;
import model.entities.Pessoa;
import model.entities.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao extends GenericDao<Produto> {

    public List<Produto> listarPorCategoria(Categoria categoria) throws Exception {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT p FROM Produto p ");
            stringBuilder.append("JOIN Categoria c ");
            stringBuilder.append("ON p.categoria = c.id ");
            stringBuilder.append("WHERE c.id = :idCategoria");

            return getEntityManager().createQuery(stringBuilder.toString())
                    .setParameter("idCategoria", categoria.getId())
                    .getResultList();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            getEntityManager().close();
        }
    }

}
