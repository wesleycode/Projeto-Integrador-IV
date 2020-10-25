package controller;

import model.bo.PessoaBO;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
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
        pessoa.setNome("");
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
    public void listarPessoasEspecifica(){
        System.out.println("QQQQQQQQQQQQ" + pessoa.getNome());
        try{
            listpessoa = new PessoaBO().listarPessoasPorNomeEspecifico(pessoa.getNome());
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            listpessoa = null;
        }
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
        return;
    }

    public void setListpessoa(List<Pessoa> listpessoa) {
        this.listpessoa = listpessoa;
    }


}