package model.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Avaliacao implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comentario;
    private int nota;
    private Date dataPostado;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Pessoa pessoa;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getDataPostado() {
        return dataPostado;
    }

    public void setDataPostado(Date dataPostado) {
        this.dataPostado = dataPostado;
    }

    public Avaliacao() {

    }

}
