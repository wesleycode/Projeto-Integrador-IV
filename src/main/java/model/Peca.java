package model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Peca")
public class Peca implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String peca;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private InformacoesPeca informacoesPeca;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPeca() {
        return peca;
    }

    public void setPeca(String peca) {
        this.peca = peca;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public InformacoesPeca getInfopeca() {
        return informacoesPeca;
    }

    public void setInfopeca(InformacoesPeca informacoesPeca) {
        this.informacoesPeca = informacoesPeca;
    }

    public Peca() {

    }
}
