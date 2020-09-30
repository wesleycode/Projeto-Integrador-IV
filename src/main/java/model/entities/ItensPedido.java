package model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "ItensPedido")
public class ItensPedido implements EntityBase {

    @Id
    private long id;
    private long quantidade;
    private long valor;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Carrinho cliente;

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
        return cliente;
    }

    public void setCliente(Carrinho carrinho) {
        this.cliente = cliente;
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

    public ItensPedido() {

    }

}
