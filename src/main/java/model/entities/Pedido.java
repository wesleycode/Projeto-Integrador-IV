package model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Pedido")
public class Pedido implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long valorTotal;
    private long quantidade;
    
    @ManyToOne
    private Pessoa pessoa;
    @ManyToOne
    private Endereco endereco;


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public long getId() {
        return id;
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

    public long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido() {

    }

}
