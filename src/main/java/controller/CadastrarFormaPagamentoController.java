package controller;

import model.bo.FormaPagamentoBO;
import model.bo.MarcaProdutoBO;
import model.entities.FormaPagamento;
import model.entities.MarcaProduto;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CadastrarFormaPagamentoController implements Serializable {
    private FormaPagamento formaPagamento;
    private List<FormaPagamento>formaPagamentoList;

    public CadastrarFormaPagamentoController() {

        formaPagamento = new FormaPagamento();
        try {
            formaPagamentoList = listarformadePagamento();
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
        }
    }

    public void cadastrarFormaPagamento() {
        long idMax = new FormaPagamentoBO().getLastId();

        if (idMax == -1) {
            formaPagamento.setId(1);
        } else {
            formaPagamento.setId(idMax + 1);
        }
        try {
            if (new FormaPagamentoBO().criar(formaPagamento)) {
                FacesMessages.info("Forma de Pagamento cadastrada com sucesso");
            }
        } catch (Exception e) {
            FacesMessages.error("Erro: " + e.getMessage());
        }
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<FormaPagamento> getFormaPagamentoList() {
        return formaPagamentoList;
    }

    public void setFormaPagamentoList(List<FormaPagamento> formaPagamentoList) {
        this.formaPagamentoList = formaPagamentoList;
    }

    public List<FormaPagamento> listarformadePagamento() throws Exception {
        try {
            return new FormaPagamentoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
            return null;
        }
    }

}