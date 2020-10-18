package model.bo;

import model.dao.ProdutoDao;
import model.entities.Categoria;
import model.entities.Produto;
import model.dao.GenericDao;

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
        if (valida(o)) {
            return new GenericDao<Produto>().salvarOuAlterar(o);
        }
        return false;
    }

    @Override
    public List<Produto> listarTodos() throws Exception {
        return new GenericDao<Produto>().listarTodos(Produto.class);
    }

    @Override
    public Produto getById(int id) throws Exception {
        return new GenericDao<Produto>().getById(Produto.class, id);
    }

    @Override
    public boolean valida(Produto o) throws Exception {
        if (o.getCategoria().getId() < 0) {
            throw new Exception("Categoria de Produto nulo");
        } else if (o.getDescricao().equals("")) {
            throw new Exception("Falta de descrição");
        }
        /*else if(o.getFormaPagamento().getId()<0){
            throw new Exception("Forma de Pagamento de produto nulo");
        } //não sei como vamos usar essa classe ainda então vou manter em comentário*/
        else if (o.getFornecedor().getId() < 0) {
            throw new Exception("Fornecedor de Produto nulo");
        } else if (o.getMarcaProduto().getId() < 0) {
            throw new Exception("Marca de Produto nulo");
        } else if (o.getNome().equals("")) {
            throw new Exception("Nome do Produto nulo");
        } else if (o.getPreco() < 0) {
            throw new Exception("Preço não Permitido");
        }
        /*else if(o.getTipoentrega().getId()<0){
            throw new Exception("Tipo Entrega nulo");
        } //não sei como vamos usar essa classe ainda então vou manter em comentário */
        return true;
    }

    @Override
    public boolean validaId(long id) throws Exception {
        if (id < 0) {
            throw new Exception("Id nulo");
        }
        return true;
    }

    public List<Produto> listarPorCategoria(Categoria categoria) throws Exception {
        if (categoria != null) {
            return new ProdutoDao().listarPorCategoria(categoria);
        }
        return null;
    }
}