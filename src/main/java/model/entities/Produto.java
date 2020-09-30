package model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Produto")
public class Produto implements EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private long preco;
    private String descricao;
    private boolean emEstoque;
    @OneToOne
    private FormaPagamento formaPagamento;
    @ManyToOne
    private MarcaProduto marcaProduto;
    @ManyToOne
    private TipoEntrega tipoentrega;
    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private Categoria categoria;

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

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
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

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public MarcaProduto getMarca() {
        return marcaProduto;
    }

    public void setMarca(MarcaProduto marca) {
        this.marcaProduto = marca;
    }

    public TipoEntrega getTipoentrega() {
        return tipoentrega;
    }

    public void setTipoentrega(TipoEntrega tipoentrega) {
        this.tipoentrega = tipoentrega;
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

    public Produto() {

    }

}
