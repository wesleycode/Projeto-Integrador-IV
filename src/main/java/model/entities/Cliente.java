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

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

}
