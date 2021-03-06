package model.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Pessoa implements EntityBase {

    @Id
    private long id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private int tipoUsuario;

    private Date dataNascimento;
    @ManyToOne
    private Endereco endereco;
    private boolean ativo;

    public int getTipoUsuario() { return tipoUsuario; }

    public void setTipoUsuario(int tipoUsuario) { this.tipoUsuario = tipoUsuario; }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoa() {
        endereco = new Endereco();
    }

}
