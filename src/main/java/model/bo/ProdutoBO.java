package model.bo;

import model.dao.ProdutoDao;
import model.entities.Categoria;
import model.entities.Produto;
import model.dao.GenericDao;
import net.bootsfaces.utils.FacesMessages;
import utilities.Moeda;

import java.util.List;

public class ProdutoBO implements GenericBO<Produto> {

    public ProdutoBO() {

    }

    @Override
    public boolean criar(Produto o) throws Exception {
        if (valida(o)) {
            return new GenericDao<Produto>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public boolean deletar(Produto o) throws Exception {
        if (validaId(o.getId())) {
            return new GenericDao<Produto>().deletar(Produto.class, o.getId());
        }
        return false;
    }

    @Override
    public boolean alterar(Produto o) throws Exception {
        if (validarAlterarId(o.getId())) {
            if (valida(o)) {
                return new GenericDao<Produto>().salvarOuAlterar(o);
            }
        }
        return false;
    }

    @Override
    public List<Produto> listarTodos() throws Exception {
        return new GenericDao<Produto>().listarTodos(Produto.class);
    }

    @Override
    public Produto getById(long id) throws Exception {
        return new GenericDao<Produto>().getById(Produto.class, id);
    }

    @Override
    public boolean valida(Produto o) throws Exception {
        if (o.getCategoria().getId() < 0) {
            throw new Exception("Categoria de Produto nulo");
        } else if (o.getDescricao().equals("")) {
            throw new Exception("Falta de descrição");
        } else if (o.getFornecedor().getId() < 0) {
            throw new Exception("Fornecedor de Produto nulo");
        } else if (o.getMarcaProduto().getId() < 0) {
            throw new Exception("Marca de Produto nulo");
        } else if (o.getNome().equals("")) {
            throw new Exception("Nome do Produto nulo");
        } else if (o.getPreco() < 0) {
            throw new Exception("Preço não Permitido");
        }
        return true;
    }

    public boolean validarAlterarId(long id) throws Exception {
        if ((id > new ProdutoBO().getLastId()) || (id <= 0)) {
            throw new Exception("Id do produto que voce digitou não existe!");
        }
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

    public static String getPrecoParceladoEm10Vezes(float preco) {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil((long) (preco / 10));
    }

    public static String getPrecoFormatado(float preco) {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil((long) (preco));
    }

    public List<Produto> listarPorCategoria(long categoriaId,String valor) throws Exception {
        if (valor == null) {
            return new ProdutoDao().listarPorCategoria(categoriaId);
        } else {
            return listarPorCategoriaComLike(categoriaId,valor);
        }
    }

    private List<Produto> listarPorCategoriaComLike(long id,String valor) throws Exception {
        return (valor != null) ? new ProdutoDao().listarPorCategoriaComLike(valor,id) : null;
    }

    public long getLastId() {
        return new GenericDao<Produto>().getLastId(Produto.class);
    }

    public List<Produto> listarProdutos(Categoria categoria , String valor) throws Exception {
        if ((categoria.getId() != 0 && categoria.getCategoria() != null) || (valor != null)) {
            return listarPorCategoria(categoria.getId(), valor);
        } else {
            try {
                return new ProdutoBO().listarTodos();
            } catch (Exception e) {
                FacesMessages.error("Erro: " + e.getMessage());
                return null;
            }
        }
    }
    public List<Produto> listarPorCategoriaComESemEstoque(long categoriaId,String valor) throws Exception {
        if (valor == null) {
            return new ProdutoDao().listarPorCategoriaComESemEstoque(categoriaId);
        } else {
            return listarPorCategoriaComLikeComESemEstoque(categoriaId,valor);
        }
    }
    private List<Produto> listarPorCategoriaComLikeComESemEstoque(long id,String valor) throws Exception {
        return (valor != null) ? new ProdutoDao().listarPorCategoriaComLikeComESemEstoque(valor,id) : null;
    }
    public List<Produto> listarProdutosComESemEstoque(Categoria categoria , String valor) throws Exception {
        if ((categoria.getId() != 0 && categoria.getCategoria() != null) || (valor != null)) {
            return listarPorCategoriaComESemEstoque(categoria.getId(), valor);
        } else {
            try {
                return new ProdutoBO().listarTodos();
            } catch (Exception e) {
                FacesMessages.error("Erro: " + e.getMessage());
                return null;
            }
        }
    }

}