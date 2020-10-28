package model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue(value = "Categoria")
public class Categoria implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoria;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria() {

    }

    public Categoria(long id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria1 = (Categoria) o;
        return id == categoria1.id &&
                Objects.equals(categoria, categoria1.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoria);
    }
}
