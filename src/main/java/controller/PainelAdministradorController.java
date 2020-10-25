package controller;

import model.bo.CidadeBO;
import model.bo.EnderecoBO;
import model.bo.EstadoBO;
import model.bo.PessoaBO;
import model.entities.Cidade;
import model.entities.Estado;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PainelAdministradorController implements Serializable {
    private Pessoa pessoa;
    private int tipoPessoaCadastro;

    public int getTipoPessoaCadastro() {
        return tipoPessoaCadastro;
    }

    public void setTipoPessoaCadastro(int tipoPessoaCadastro) {
        this.tipoPessoaCadastro = tipoPessoaCadastro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PainelAdministradorController() {
        pessoa = new Pessoa();
    }

    public String irParaCadastarusuário() {
        return "cadastrarusuarioViaAdm?faces-redirect=true";
    }

    public String irParaModificarUsusario() {
        return "modificarusuario?faces-redirect=true";
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

    public void cadastrar(){
        switch (getTipoPessoaCadastro()) {
            case 1:
                cadastrarNovoCliente();
                break;
            case 2:
                cadastrarNovoVendedor();
                break;
            case 3:
                cadastrarNovoAdministrador();
                break;
            default:
                FacesMessages.info("Selecione um tipo de login!");
                break;
        }
    }

    private void cadastrarUsuario() {

        if(pessoa.getEndereco().getCidade().getId() < 0){
            FacesMessages.error("Informe a Cidade");
        }else if(pessoa.getEndereco().getCidade().getEstado().getId() < 0){
            FacesMessages.error("Informe o Estado");
        }else {
            try {
                pessoa.setEndereco(new EnderecoBO().listarultimoendereco());
                if (new EnderecoBO().criar(pessoa.getEndereco())) {
                    FacesMessages.info("Endereco cadastrado com sucesso");
                } else {
                    FacesMessages.error("Erro ao cadastrar endereço do usuario");
                }
            } catch (Exception e) {
                FacesMessages.error("Erro: " + e.getMessage());
            }
            // Grava o cliente //
            try {
                System.out.println("Endereço ID: "+pessoa.getEndereco().getId());
                if (new PessoaBO().criar(pessoa)) {
                    FacesMessages.info("Usuário Cadastrado com sucesso");
                } else {
                    FacesMessages.error("Erro ao cadastrar usuário");
                }
            } catch (Exception e) {
                FacesMessages.error("Erro: " + e.getMessage());
            }
        }
    }

    private void cadastrarNovoCliente() {
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(1);
        cadastrarUsuario();
    }

    private void cadastrarNovoVendedor() {
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(2);
        cadastrarUsuario();
    }

    private void cadastrarNovoAdministrador() {
        pessoa.setAtivo(true);
        pessoa.setTipoUsuario(3);
        cadastrarUsuario();
    }

}