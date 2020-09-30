package model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Infopeca")
public class InformacoesPeca implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String informacoes;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfopeca() {
        return informacoes;
    }

    public void setInfopeca(String informacoes) {
        this.informacoes = informacoes;
    }

    public InformacoesPeca() {

    }
}
