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
import java.text.SimpleDateFormat;
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

    public void upload(FileUploadEvent event) {
        System.out.println("11111111111111");
        produto.getFotoProduto().setFoto(getFileName(event.getFile().getFileName()));
        System.out.println("2222222222");
        FacesMessage msg = new FacesMessage("Sucesso! foi carregado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            copyFile(produto.getFotoProduto().getFoto(), event.getFile().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File("web/resources/img/"+this.retornardiretorio(produto.getCategoria().getCategoria())
                    + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("Novo arquivo criado '"+fileName+"'!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public String getFileName(String nomeArquivo) {
        String data = "yyyy-MM-dd";
        String hora = "HH-mm-ss-SSS";
        String data1, hora1;

        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        data1 = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        hora1 = formata.format(agora);
        return data1 + "-" + hora1+"."+getExtensao(nomeArquivo);
    }
    public String getExtensao(String nomeArquivo)
    {
        int posicaoPonto = nomeArquivo.lastIndexOf(".");
        int tamanhoString = nomeArquivo.length();
        return nomeArquivo.substring(posicaoPonto + 1, tamanhoString);
    }

    public void cadastrarMarcaProduto(){
        try {

            System.out.println("555555555 : "+produto.getFotoProduto().getFoto());
            System.out.println("!!!!!!!!!!!!");
            produto.setEmEstoque(true);

            if (new ProdutoBO().criar(produto)) {
                FacesMessages.info("Produto cadastrada com sucesso");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro Final:" + e.getMessage());
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