package controller;

import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@Named
@ViewScoped
public class CadastrarProdutoController implements Serializable {
    private Produto produto;
    private List<MarcaProduto> marcalist;
    private List<Categoria> categoriaList;
    private List<Fornecedor>fornecedorList;
    private UploadedFile file;

    public String retornardiretorio(String categoria1){
        switch (categoria1){
            case "Memoria Ram":
                return "memorias/";
            case "HDs":
                    return "hds/";
            case "SSDs":
                return "ssds/";
            case "Processadores":
                    return "processadores/";
            case "Placas de Video":
                return "placa de video/";
            case "Placas de Som":
                return "placa de som/";
            case "Placas Mãe":
                return "placa mae/";
            case "Coolers":
                return "coolers/";
            case "Monitores":
                return "monitores/";
            case "Notebooks":
                return "notebooks/";
            case "Softwares":
                return "softwares/";
            case "Periféricos":
                return "perifericos/";
            case "Fontes":
                return "fontes/";
            default:
                FacesMessages.error("Erro ao localizar diretório categoria");
        }
        return null;
    }

    public CadastrarProdutoController() {
        produto = new Produto();
        categoriaList = listarCategoria();
        marcalist = listarMarca();
        fornecedorList = listarFornecedor();
    }


    public List<MarcaProduto> listarMarca() {
        MarcaProdutoBO marcaProdutoBO = new MarcaProdutoBO();
        try {
            return marcaProdutoBO.listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Categoria> listarCategoria() {
        CategoriaBO categoriaBO = new CategoriaBO();
        try {
            return categoriaBO.listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Fornecedor> listarFornecedor() {
        FornecedorBO fornecedorBO = new FornecedorBO();
        try {
            return fornecedorBO.listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }


    public String irParaPainelVendedor() {
        return "painelVendedor?faces-redirect=true";
    }

    public String irParaIndex() {
        return "index?faces-redirect=true";
    }

    public void cadastrarFoto(FileUploadEvent event){
        System.out.println("Entra?");
        try {
        FotoProduto foto = new FotoProduto();
        foto.setFoto(this.retornardiretorio(produto.getCategoria().getCategoria()) + event.getFile().getFileName());
        File file1 = new File("resources/img/"+this.retornardiretorio(produto.getCategoria().getCategoria()), event.getFile().getFileName());
        System.out.println("aaaaaaaaaaaaaaaa");
        OutputStream out = new FileOutputStream(file1);
        out.write(getFile().getContent());
        out.close();
        System.out.println("bbbbbbbbbbbbbbbbbb");
        if (new FotoProdutoBO().criar(foto)) {
            produto.setFotoProduto(foto);
            FacesMessages.info("Foto Produto cadastrado com sucesso");
        }
        } catch (Exception e) {
            FacesMessages.error("Erro Ao cadastrar Foto:" + e.getMessage());
        }
    }
    public void savefoto() throws IOException {
        System.out.println("111111");
        FotoProduto foto = new FotoProduto();
        String filename = FilenameUtils.getName(file.getFileName());
        System.out.println("2222222");
        InputStream input = file.getInputStream();
        OutputStream output = new FileOutputStream(new File("resources/img/"+this.retornardiretorio(produto.getCategoria().getCategoria()), filename));
        foto.setFoto(this.retornardiretorio(produto.getCategoria().getCategoria()) + filename);
        System.out.println("3333333");
        try {
            IOUtils.copy(input, output);
            System.out.println("4444444444");
            if (new FotoProdutoBO().criar(foto)) {
                produto.setFotoProduto(foto);
                FacesMessages.info("Foto Produto cadastrado com sucesso");
            }
        }catch (Exception e) {
            FacesMessages.error("Erro Ao cadastrar Foto:" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    public void cadastrarMarcaProduto(){
        try {
            savefoto();
            System.out.println("555555555");
            produto.setEmEstoque(true);

            if (new ProdutoBO().criar(produto)) {
                FacesMessages.info("Produto cadastrada com sucesso");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro Final:" + e.getMessage());
        }
    }
    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Imagem:", file.getFileName() + " recebida.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<MarcaProduto> getMarcalist() {
        return marcalist;
    }

    public void setMarcalist(List<MarcaProduto> marcalist) {
        this.marcalist = marcalist;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }
}