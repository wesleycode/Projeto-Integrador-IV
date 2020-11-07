package controller;

import model.bo.*;
import model.dao.FotoProdutoDao;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;
import utilities.Strings;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;

@Named
@RequestScoped
public class CadastrarProdutoController implements Serializable {

    private Produto produto;
    private List<MarcaProduto> marcalist;
    private List<Categoria> categoriaList;
    private List<Fornecedor> fornecedorList;
    private Part arquivo;

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
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

    public CadastrarProdutoController() {
        produto = new Produto();
        categoriaList = listarCategoria();
        marcalist = listarMarca();
        fornecedorList = listarFornecedor();
    }

    public List<MarcaProduto> listarMarca() {
        try {
            return new MarcaProdutoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Categoria> listarCategoria() {
        try {
            return new CategoriaBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public List<Fornecedor> listarFornecedor() {
        try {
            return new FornecedorBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
        return null;
    }

    public void cadastrarProduto() {
        try {

            if (ArquivoBO.validarImagem(arquivo)) {

                String nomeArquivo = arquivo.getSubmittedFileName();
                String nomeCategoria = Strings.removerAcentosEspaco(produto.getCategoria()
                        .getCategoria().toLowerCase());

                StringBuilder stringBuilder = new StringBuilder();

                // Felipe descomentar isso e comentar a primeira linha do meu abaixo //
                //stringBuilder.append("C:\\Users\\lino\\Documents\\Intellij\\Projeto-Integrador-IV");

                // Wesley descomentar esse e comentar o do Felipe acima //
                stringBuilder.append("D:\\Projetos\\Projeto-Integrador-IV");

                stringBuilder.append("\\web\\resources\\img\\");
                stringBuilder.append(nomeCategoria);
                stringBuilder.append("\\");

                String nomeDoArquivo = Strings.formatarStringComDataAtual(nomeArquivo);

                Files.copy(arquivo.getInputStream(),
                        new File(stringBuilder.toString(),
                                nomeDoArquivo).toPath());

                FotoProduto fotoProduto = new FotoProduto();

                long lastIdFotoProduto = new FotoProdutoBO().getLastId();

                if (lastIdFotoProduto == -1) {
                    fotoProduto.setId(1);
                } else {
                    fotoProduto.setId(lastIdFotoProduto + 1);
                }

                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(nomeCategoria);
                stringBuilder1.append("/");
                stringBuilder1.append(nomeDoArquivo);

                fotoProduto.setFoto(stringBuilder1.toString());

                long lastIdProduto = new ProdutoBO().getLastId();

                if (lastIdProduto == -1) {
                    produto.setId(1);
                } else {
                    produto.setId(lastIdProduto + 1);
                }

                produto.setFotoProduto(fotoProduto);
                produto.setEmEstoque(true);

                if (new FotoProdutoBO().criar(fotoProduto)) {
                    if (new ProdutoBO().criar(produto)) {
                        FacesMessages.info("Produto cadastrado com Sucesso!");
                    }
                }

            }

        } catch (Exception e) {
            FacesMessages.error("Erro:" + e.getMessage());
        }
    }
}