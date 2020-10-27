package controller;

import model.bo.MarcaProdutoBO;
import model.bo.PessoaBO;
import model.entities.MarcaProduto;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;
import net.bootsfaces.utils.FacesMessages;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AlterarUsuarioController implements Serializable {
    private Pessoa pessoa;
    private String nome1;
    private List<Pessoa>listpessoa;

    public AlterarUsuarioController() {
        pessoa = new Pessoa();
        pessoa.setNome("");
        listpessoa = listarPessoas();
    }

    public String getNome1() {
        return nome1;
    }

    public void setNome1(String nome1) {
        this.nome1 = nome1;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListpessoa() {
        return listpessoa;
    }

    public List<Pessoa> listarPessoas(){
        try{
            return new PessoaBO().listarPessoasPorNome();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }
    public void listarPessoasEspecifica(String a){
        System.out.println("QQQQQQQQQQQQ" + a);
        try{
            listpessoa = new PessoaBO().listarPessoasPorNomeEspecifico(a);
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            listpessoa = null;
        }
    }
    public void isAtivoOuDesativo(int id){
        System.out.println("esta entrando aqui?");
        try{
            Pessoa p1 = new PessoaBO().getById(id);
            if(p1.isAtivo()){
                p1.setAtivo(false);
            }else{
                p1.setAtivo(true);
            }
            new PessoaBO().alterar(p1);
            FacesMessages.info("A pessoa Foi Alterada");
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return;
    }

    public void setListpessoa(List<Pessoa> listpessoa) {
        this.listpessoa = listpessoa;
    }



}