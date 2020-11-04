package model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Produto")
public class Produto implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private float preco;
    private String descricao;
    private boolean emEstoque;

    @ManyToOne
    private MarcaProduto marcaProduto;
    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private FotoProduto fotoProduto;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isEmEstoque() {
        return emEstoque;
    }

    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    }

    public MarcaProduto getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(MarcaProduto marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public FotoProduto getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(FotoProduto fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

    public Produto() {
        marcaProduto = new MarcaProduto();
        fornecedor = new Fornecedor();
        categoria = new Categoria();
        fotoProduto = new FotoProduto();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", marcaProduto=" + marcaProduto +
                ", fornecedor=" + fornecedor +
                ", categoria=" + categoria +
                '}';
    }
}
