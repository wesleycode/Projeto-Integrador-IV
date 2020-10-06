package model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "Cliente")
public class Cliente extends Pessoa implements EntityBase {

    @ManyToOne
    private Carrinho carrinho;
    @ManyToOne
    private Endereco endereco;

    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente() {
        carrinho = new Carrinho();
        endereco = new Endereco();
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

}
