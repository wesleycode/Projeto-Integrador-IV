package controller;

import model.bo.CidadeBO;
import model.bo.EstadoBO;
import model.bo.ProdutoBO;
import model.dao.EstadoDao;
import model.dao.GenericDao;
import model.entities.Cidade;

import model.entities.Estado;
import model.entities.Produto;

import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PesquisaController implements Serializable {

    private List<Estado> todosProdutos;
    private Estado produto;

    private int totaldepag = 5;
    private int pagatual = 1;
    private int countpage;

    public PesquisaController() {

    }

    public List<Estado> getTodosProdutos() throws Exception{
        try {
            this.todosProdutos = new EstadoDao().listarEstadoEntre(pagatual,totaldepag);
            return todosProdutos;
        } catch (Exception e) {
            System.out.println("Deu ruim get TododsProduto");
        }
        return todosProdutos;
    }

    public void setTodosProdutos(List<Estado> todosProdutos) {
        this.todosProdutos = todosProdutos;
    }

    public int getTotaldepag() {
        return totaldepag;
    }

    public void setTotaldepag(int totaldepag) {
        this.totaldepag = totaldepag;
    }

    public int getPagatual() {
        return pagatual;
    }

    public void setPagatual(int pagatual) {
        this.pagatual = pagatual;
    }


    public void nextPag() throws Exception {
        System.out.println("Next");
        if(pagatual == getCountpage()){
            pagatual = 1;
        }else {
            pagatual++;
        }
    }
    public void previusPag() throws Exception {
        if(pagatual <= 1){
            pagatual = getCountpage();
        }else {
            pagatual--;
        }
    }

    public Estado getProduto() {
        return produto;
    }

    public void setProduto(Estado produto) {
        this.produto = produto;
    }

    public int getCountpage() throws Exception {
        try {
            EstadoBO estadoBO = new EstadoBO();
            countpage = (int) Math.ceil(estadoBO.listarTodos().size()/(double)totaldepag);
            return countpage;

        } catch (Exception e) {
            System.out.println("Deu ruim get Countpage :(");
        }
        return countpage;
    }

    public void setCountpage(int countpage) {
        this.countpage = countpage;
    }
}
