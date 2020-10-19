package controller;

import model.bo.CategoriaBO;
import model.bo.EnderecoBO;
import model.bo.MarcaProdutoBO;
import model.bo.ProdutoBO;
import model.entities.Categoria;
import model.entities.MarcaProduto;
import model.entities.Produto;
import net.bootsfaces.utils.FacesMessages;
import utilities.Moeda;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CadastrarMarcaController implements Serializable {
    private MarcaProduto marca;
    private List<MarcaProduto>marcalist;


    public CadastrarMarcaController(){
        marca = new MarcaProduto();
    }

    public List<MarcaProduto> getMarcalist() {
        return marcalist;
    }

    public void setMarcalist(List<MarcaProduto> marcalist) {
        this.marcalist = marcalist;
    }

    public MarcaProduto getMarcaProduto() {
        return marca;
    }

    public void setCategoria(MarcaProduto marca) {
        this.marca = marca;
    }

    public String irParaPainelVendedor() {
        return "painelvendedor?faces-redirect=true";
    }
    public String irParaIndex() {
        return "index?faces-redirect=true";
    }

    public void cadastrarMarcaProduto() {
        try {
            if (new MarcaProdutoBO().criar(marca)){
                FacesMessages.info("MarcaProduto cadastrada com sucesso");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

    public List<MarcaProduto> getTodasMarcaProdutoList() throws Exception {
        try {
            return new MarcaProdutoBO().listarTodos();
        } catch (Exception e) {
            throw new Exception("Erro ao retornar a list deMarcaProduto: " + e.getMessage());
        }
    }

}