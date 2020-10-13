package controller;


import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PesquisaController implements Serializable {

    private String pesquisa;
    private Categoria categoriaselecionada;
    private Produto produtoselecionado;
    private List<Categoria> categoriasList;
    private List<String> filtros;

    public void selecionarCategoria() {
        int idcatg = (int) categoriaselecionada.getId();
        switch (idcatg) {

            case 0://Memoria Ram
                filtros.clear();
                //RAM total
                filtros.add("2GB");
                filtros.add("4GB");
                filtros.add("6GB");
                filtros.add("8GB");
                filtros.add("16GB");
                //MHZs
                filtros.add("2666MHz");
                filtros.add("2400MHz");
                filtros.add("2666Mhz");
                //DDR
                filtros.add("DDR3");
                filtros.add("DDR4");
                break;
            case 1://HDs
                filtros.clear();
                //tamanho
                filtros.add("100GB");
                filtros.add("150GB");
                filtros.add("200GB");
                filtros.add("500GB");
                filtros.add("1TB");
                break;
            case 2://SSDs
                filtros.clear();
                //tamanho
                filtros.add("100GB");
                filtros.add("150GB");
                filtros.add("200GB");
                filtros.add("500GB");
                break;
            case 3://Processadores
                filtros.clear();
                //tamanho
                filtros.add("I3");
                filtros.add("I5");
                filtros.add("I7");
                break;
            case 4://Placas de Video
                filtros.clear();
                break;
            case 5://Placas de Som
                filtros.clear();
                break;
            case 6://Placas MÃE
                filtros.clear();
                break;
            case 7://Coolers
                filtros.clear();
                break;
            case 8://Monitores
                filtros.clear();
                break;
            case 9://Notebooks
                filtros.clear();
                //RAM total
                filtros.add("2GB");
                filtros.add("4GB");
                filtros.add("6GB");
                filtros.add("8GB");
                filtros.add("16GB");
                //HD
                //tamanho
                filtros.add("100GB");
                filtros.add("150GB");
                filtros.add("200GB");
                filtros.add("500GB");
                filtros.add("1TB");
                //processador
                filtros.add("I3");
                filtros.add("I5");
                filtros.add("I7");
                break;
            case 10://Softwares
                filtros.clear();
                break;
            case 11://Perifericos
                filtros.clear();
                break;
            case 12://Fontes
                filtros.clear();
                break;
            default:
                filtros.clear();
                break;

            //mano so digo uma coisa
            //13 categorias sendo filtros especificos pra cada
            // quem vai preencher sa poha com produtos é vc!!!

        }
    }

    public List<Categoria> getCategoriasList() throws Exception { return new CategoriaBO().listarTodos(); }

    public void setCategoriasList(List<Categoria> categoriasList) {
        this.categoriasList = categoriasList;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Categoria getCategoriaselecionada() {
        return categoriaselecionada;
    }

    public void setCategoriaselecionada(Categoria categoriaselecionada) { this.categoriaselecionada = categoriaselecionada; }

    public List<String> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<String> filtros) {
        this.filtros = filtros;
    }
}
