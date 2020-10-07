package model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "Cliente")
public class Cliente extends Pessoa implements EntityBase {

    public Cliente() {

    }

}
