package model.entities;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "Fornecedor")
public class Fornecedor implements EntityBase {

    @Id
    private long id;
    private String Fornecedor;
    private String hyperlink;   //link do fornecedor pro site

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public Fornecedor() {

    }
   
}
