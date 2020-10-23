package controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexController implements Serializable {

    public String irDiretocomovendedor() { return "painelVendedor?faces-redirect=true"; }

    public String irParaPoliticas() {
        return "politicas?faces-redirect=true";
    }

    public String irParaFornecedores() {
        return "fornecedores?faces-redirect=true";
    }

    public String irParaSobrenos() {
        return "sobrenos?faces-redirect=true";
    }

    public String getRedirecionamentoParaCadastro() {
        return "cadastroLogin?faces-redirect=true";
    }

    public String getRedirecionamentoParaIndex() { return "index?faces-redirect=true"; }

}
