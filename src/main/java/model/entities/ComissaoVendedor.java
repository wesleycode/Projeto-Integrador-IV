package model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity
@DiscriminatorValue(value = "ComissaoVendedor")
public class ComissaoVendedor implements EntityBase {

    @Id
    private long id;
    private double totalComissao;
    private Date dataComissao;

    @ManyToOne
    private Pessoa pessoa;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalComissao() {
        return totalComissao;
    }

    public void setTotalComissao(double totalComissao) {
        this.totalComissao = totalComissao;
    }

    public Date getDataComissao() {
        return dataComissao;
    }

    public void setDataComissao(Date dataComissao) {
        this.dataComissao = dataComissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ComissaoVendedor() {

    }
   
}
