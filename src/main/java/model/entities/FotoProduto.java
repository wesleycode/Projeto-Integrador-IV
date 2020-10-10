package model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "FotoProduto")
public class FotoProduto implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String foto;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public FotoProduto() {

    }
}
