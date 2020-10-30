package controller;

import model.bo.CidadeBO;
import model.bo.EnderecoBO;
import model.bo.EstadoBO;
import model.bo.PessoaBO;
import model.entities.Cidade;
import model.entities.Estado;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AlterarUsuarioController implements Serializable {
    private Pessoa pessoaModificar;
    private List<Pessoa>listpessoa;
    private List<Estado>estados;
    private List<Cidade>cidades;
    private Pessoa pessoadeFundo;

    public AlterarUsuarioController() {
        pessoaModificar = new Pessoa();
        listpessoa = listarPessoas();
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Pessoa getPessoaModificar() {
        return pessoaModificar;
    }

    public void setPessoaModificar(Pessoa pessoaModificar) {
        this.pessoaModificar = pessoaModificar;
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
        System.out.println("Entra ai vagaba");
        try{
            listpessoa = new PessoaBO().listarPessoasPorNomeEspecifico(pessoaModificar.getNome());
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            listpessoa = null;
        }
    }
    public void isAtivoOuDesativo(Pessoa p){
        try{
            if(p.isAtivo()){
                p.setAtivo(false);
            }else{
                p.setAtivo(true);
            }
            new PessoaBO().alterar(p);
            FacesMessages.info("A pessoa Foi Alterada");
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return;
    }
    public List<Estado> listarTodosOsEstados() {
        try {
            return new EstadoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }

    public List<Cidade> listarTodosAsCidadesPorEstado(Estado estado) {
        try {
            return new CidadeBO().listarCidadePorEstado(estado);
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
            return null;
        }
    }
    public void modificarPessoa(){
        try{
            new EnderecoBO().alterar(pessoaModificar.getEndereco());
            new PessoaBO().alterar(pessoaModificar);
            FacesMessages.info("A pessoa Foi Alterada");
        } catch (Exception e) {
            try{
                pessoaModificar.getEndereco().setCidade(pessoadeFundo.getEndereco().getCidade());
                new EnderecoBO().alterar(pessoaModificar.getEndereco());
                new PessoaBO().alterar(pessoaModificar);
                FacesMessages.info("A pessoa Foi Alterada");
            } catch (Exception d) {
                FacesMessages.error("Erro: " + e.getMessage());
            }
        }
    }

    public void setListpessoa(List<Pessoa> listpessoa) {
        this.listpessoa = listpessoa;
    }

    public String irParaModificarUsuarioviaADM(Pessoa p){
        pessoaModificar = p;
        pessoadeFundo = pessoaModificar;
        estados = listarTodosOsEstados();
        cidades=listarTodosAsCidadesPorEstado(pessoaModificar.getEndereco().getCidade().getEstado());
        return RedirecionamentoController.irParaModificarusuarioViaAdm();
    }


}