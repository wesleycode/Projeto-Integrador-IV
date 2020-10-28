package model.bo;

import model.dao.AvaliacaoDao;
import model.dao.GenericDao;
import model.entities.Avaliacao;
import model.entities.Produto;

import java.util.List;

public class AvaliacaoBO implements GenericBO<Avaliacao> {

    private GenericDao<Avaliacao> genericDAO;

    public AvaliacaoBO() {

    }

    @Override
    public boolean criar(Avaliacao o) throws Exception {
        if (valida(o)) {
            AvaliacaoDao avaliacaodao = new AvaliacaoDao();
            if (avaliacaodao.isAvaliacaoExisteNoBancoDeDados(o)) {
                return alterar(o);
                //caso o cliente tenha realizaddo uma avaliação de um mesmo protudo, será mudado
                //em cima do já existente
            } else {
                return avaliacaodao.salvarOuAlterar(o);
            }
        }
        return false;
    }

    @Override
    public boolean deletar(Avaliacao o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.deletar(Avaliacao.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Avaliacao o) throws Exception {
        if (valida(o)) {
            genericDAO = new GenericDao<>();
            return genericDAO.salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<Avaliacao> listarTodos() throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.listarTodos(Avaliacao.class);
    }

    @Override
    public Avaliacao getById(long id) throws Exception {
        genericDAO = new GenericDao<>();
        return genericDAO.getById(Avaliacao.class, id);
    }

    @Override
    public boolean valida(Avaliacao o) throws Exception {
        if (o.getPessoa().equals(null)) {
            throw new Exception("Avaliação sem Pessoa definido!");
        }
        if (o.getProduto().equals(null)) {
            throw new Exception("Avaliação sem Produto definido!");
        }
        validaId(o.getId());

        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

    public List<Avaliacao> listarAvaliacaoPorNota() throws Exception {
        AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
        return avaliacaoDao.listarAvaliacaoPorNota();
    }

    public List<Avaliacao> listarAvaliacaoPorProduto(Produto produto) throws Exception {
        return new AvaliacaoDao().listarAvaliacaoPorProduto(produto);
    }
}
