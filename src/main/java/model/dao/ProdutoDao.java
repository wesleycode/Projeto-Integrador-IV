package model.dao;

import connections.ConnectionFactory;
import model.entities.Avaliacao;
import model.entities.Categoria;
import model.entities.Pessoa;
import model.entities.Produto;
import utilities.Strings;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao extends GenericDao<Produto> {

    private EntityManager entityManager;
    public  ProdutoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public List<Produto> listarPorCategoria(long categoriaId) throws Exception {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT p FROM Produto p ");
            stringBuilder.append("JOIN Categoria c ");
            stringBuilder.append("ON p.categoria = c.id ");
            stringBuilder.append("WHERE c.id = :idCategoria ");
            stringBuilder.append("ADN c.emestoque = true");
            return getEntityManager().createQuery(stringBuilder.toString())
                    .setParameter("idCategoria", categoriaId)
                    .getResultList();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            getEntityManager().close();
        }
    }

    public List<Produto> listarPorCategoriaComLike(String valor, long id) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        String valores[] = valor.toLowerCase().split("\\s+");

        stringBuilder.append("SELECT p FROM Produto p ");
        stringBuilder.append("JOIN Categoria c ");
        stringBuilder.append("ON p.categoria = c.id ");
        stringBuilder.append("WHERE (c.id = :idCategoria) ");
        stringBuilder.append("AND (");
        for (int x = 0; x < valores.length; x++) {
            stringBuilder.append("lower(p.nome) LIKE '%");
            stringBuilder.append(valores[x]);
            stringBuilder.append("%' ");
            stringBuilder.append("OR lower(p.descricao) LIKE '%");
            stringBuilder.append(valores[x]);
            if (x != valores.length - 1) {
                stringBuilder.append("%' OR ");
            } else {
                stringBuilder.append("%') ");
            }
            stringBuilder.append("AND (");
            stringBuilder.append("p.emEstoque = true)");
        }

        try {
            return getEntityManager().createQuery(stringBuilder.toString())
                    .setParameter("idCategoria", id)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            getEntityManager().close();
        }
    }
}
