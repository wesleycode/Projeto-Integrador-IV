package controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RedirecionamentoController implements Serializable {

    public static String irDiretocomovendedor() { return "painelVendedor?faces-redirect=true"; }

    public static String irParaPoliticas() {
        return "politicas?faces-redirect=true";
    }

    public static String irParaFornecedores() {
        return "fornecedores?faces-redirect=true";
    }

    public static String irParaSobrenos() {
        return "sobrenos?faces-redirect=true";
    }

    public static String irParaCadastro() { return "cadastroLogin?faces-redirect=true"; }

    public static String irParaIndex() { return "index?faces-redirect=true"; }

    public static String irParaTermoCompra() { return "termodecompra.xhtml?faces-redirect=true"; }

    public static String irParaCarrinho() { return "carrinho.xhtml?faces-redirect=true"; }

    public static String irParaMeusPedidos() { return "meusPedidos.xhtml?faces-redirect=true"; }

    public static String irParaContato() { return "contato.xhtml?faces-redirect=true"; }
}
