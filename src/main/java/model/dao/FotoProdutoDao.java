package model.dao;

import connections.ConnectionFactory;
import model.entities.FotoProduto;
import model.entities.MarcaProduto;
import model.entities.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class FotoProdutoDao extends GenericDao<FotoProduto> {

    private EntityManager entityManager;

    public FotoProdutoDao(){
        entityManager = new ConnectionFactory().getConnection();
    }

    public FotoProduto listarFotoPorNome(String nome) throws Exception {
        try {
            return (FotoProduto) entityManager.createQuery("SELECT fp FROM FotoProduto fp where fp.foto = :nome")
                    .setParameter("nome",nome)
                    .getResultList().get(0);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

}
