package controller;

import model.bo.PessoaBO;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class modificarusuarioController implements Serializable {
    private Pessoa pessoa;
    private List<Pessoa>listpessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public modificarusuarioController() {
        pessoa = new Pessoa();
        listpessoa = listarPessoas();
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
    private List<Pessoa> listarPessoasEspecifica(){
        try{
            return new PessoaBO().listarPessoasPorNomeEspecifico(pessoa.getNome());
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }
    public List<Pessoa> listarPessoasViaNome(String nome){
        if(nome == ""){
            return listarPessoas();
        }else return listarPessoasEspecifica();

    }
    public void setAtivoOuDesativo(Pessoa p1){
        System.out.println("esta entrando aqui?");
        if(p1.isAtivo()){
            p1.setAtivo(false);
        }else{
            p1.setAtivo(true);
        }
        try{
            new PessoaBO().alterar(p1);
            FacesMessages.info("A pessoa Foi Alterada");
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

    public void setListpessoa(List<Pessoa> listpessoa) {
        this.listpessoa = listpessoa;
    }


}