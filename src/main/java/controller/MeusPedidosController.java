package controller;

import model.bo.PedidoBO;
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
public class MeusPedidosController implements Serializable {
    private List<Pedido> pedidoList;
    private Pessoa pessoa;
    public MeusPedidosController() {
        pessoa = (Pessoa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pessoa");
        pedidoList = listarPedidosDePessoa();
    }
    public List<Pedido> listarPedidosDePessoa(){
        try {
            return new PedidoBO().listarpedidosDaPessoa(pessoa);
        }catch (Exception e){
            FacesMessages.error("Erro ao listar Pedidos: " + e.getMessage());
            return null;
        }
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }
}
