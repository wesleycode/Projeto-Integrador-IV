package model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue(value = "ItensCarrinho")
public class ItemCarrinho implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long quantidade;
    private long valor;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Carrinho Carrinho;

    @Override
    public long getId() {
        return id;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Carrinho getCarrinho() {
        return Carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        Carrinho = carrinho;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public ItemCarrinho() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarrinho that = (ItemCarrinho) o;
        return id == that.id &&
                quantidade == that.quantidade &&
                valor == that.valor &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(Carrinho, that.Carrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, valor, produto, Carrinho);
    }
}
