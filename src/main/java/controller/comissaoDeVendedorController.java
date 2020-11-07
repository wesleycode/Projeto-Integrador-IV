package controller;

import model.bo.ComissaoVendedorBO;
import model.bo.MarcaProdutoBO;
import model.bo.PedidoBO;
import model.entities.ComissaoVendedor;
import model.entities.MarcaProduto;
import model.entities.Pedido;
import model.entities.Pessoa;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class comissaoDeVendedorController implements Serializable {
   private Pessoa pessoa;
   private List<ComissaoVendedor> listcomissaoVendedor;

   public comissaoDeVendedorController(){
       pessoa = (Pessoa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pessoa");
        listcomissaoVendedor = listarComissaoDePessoa();
   }
    public List<ComissaoVendedor> listarComissaoDePessoa(){
        try {
            return new ComissaoVendedorBO().listarComissaoPorPessoa(pessoa);
        }catch (Exception e){
            FacesMessages.error("Erro ao listar Pedidos: " + e.getMessage());
            return null;
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ComissaoVendedor> getListcomissaoVendedor() {
        return listcomissaoVendedor;
    }

    public void setListcomissaoVendedor(List<ComissaoVendedor> listcomissaoVendedor) {
        this.listcomissaoVendedor = listcomissaoVendedor;
    }
}