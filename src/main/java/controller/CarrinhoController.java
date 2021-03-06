package controller;

import model.bo.*;
import model.entities.*;
import net.bootsfaces.utils.FacesMessages;
import utilities.Comissao;
import utilities.Moeda;
import utilities.Tempo;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CarrinhoController implements Serializable {

    private List<ItemCarrinho> itemCarrinhoList;
    private Pessoa pessoa;
    private Endereco endereco;
    private Carrinho carrinho;
    private ItemCarrinho itemCarrinho;
    private int quantidadeItem;
    private String valorCupom;
    private boolean cupomAdicionado;
    private double valorTotalInicialCarrinho;
    private List<FormaPagamento> formaPagamentoList;
    private FormaPagamento formaPagamento;

    public String getValorCupom() {
        return valorCupom;
    }

    public void setValorCupom(String valorCupom) {
        this.valorCupom = valorCupom;
    }

    public ItemCarrinho getItemCarrinho() {
        return itemCarrinho;
    }

    public void setItemCarrinho(ItemCarrinho itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }

    public List<ItemCarrinho> getItemCarrinhoList() {
        return itemCarrinhoList;
    }

    public void setItemCarrinhoList(List<ItemCarrinho> itemCarrinhoList) {
        this.itemCarrinhoList = itemCarrinhoList;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ItemCarrinho> getItensCarrinhoList() {
        return itemCarrinhoList;
    }

    public void setItensCarrinhoList(List<ItemCarrinho> itemCarrinhoList) {
        this.itemCarrinhoList = itemCarrinhoList;
    }

    public List<FormaPagamento> getFormaPagamentoList() {
        return formaPagamentoList;
    }

    public void setFormaPagamentoList(List<FormaPagamento> formaPagamentoList) {
        this.formaPagamentoList = formaPagamentoList;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public CarrinhoController() {
        cupomAdicionado = false;
        valorCupom = "";
        formaPagamentoList = listarTodasFormasDePagamento();
        pessoa = new Pessoa();
        itemCarrinhoList = new ArrayList<>();
        itemCarrinho = new ItemCarrinho();
        quantidadeItem = 1;
        endereco = new Endereco();
        carrinho = new Carrinho();
    }

    public void comprarProduto(Pessoa vendedor) {

        System.out.println("Comprando Produto...");
        System.out.println("-------------------");


        try {

            carrinho.setCliente(pessoa);
            carrinho.setQuantidade(itemCarrinhoList.size());
            carrinho.setValorTotal(new CarrinhoBO().valorTotalDoCarrinho(itemCarrinhoList));

            System.out.println("-------------------");

            if (new CarrinhoBO().valida(carrinho)) {

                if (new FormaPagamentoBO().valida(formaPagamento)) {

                    System.out.println("Forma de pagamento validada...");

                    Pedido pedido = new Pedido();
                    pedido.setPessoa(pessoa);
                    pedido.setEndereco(pessoa.getEndereco());
                    pedido.setFormaPagamento(formaPagamento);
                    pedido.setValorTotal(carrinho.getValorTotal());
                    pedido.setQuantidade(carrinho.getQuantidade());


                    if (new PedidoBO().valida(pedido)) {

                        long idUltimoPedidoNoBanco = new PedidoBO().getUltimoId();

                        if (idUltimoPedidoNoBanco == -1) {
                            pedido.setId(1);
                        } else {
                            pedido.setId(idUltimoPedidoNoBanco + 1);
                        }

                        System.out.println("Pedido Validado...");
                        System.out.println("Salvando Pedido...");
                        new PedidoBO().criar(pedido);
                        System.out.println("Pedido Salvo...");

                        for (ItemCarrinho itemCarrinho : itemCarrinhoList) {

                            ItemPedido itemPedido = new ItemPedido();

                            itemPedido.setPedido(pedido);
                            itemPedido.setProduto(itemCarrinho.getProduto());
                            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
                            itemPedido.setValor(itemCarrinho.getValor());

                            System.out.println("-------------------");

                            System.out.println("ITEM PEDIDO ID: " + itemPedido.getPedido().getId());
                            System.out.println("ITEM PEDIDO PRODUTO ID: " + itemPedido.getProduto().getId());
                            System.out.println("ITEM PEDIDO QUANTIDADE: " + itemPedido.getQuantidade());
                            System.out.println("ITEM PEDIDO VALOR: " + itemPedido.getValor());

                            System.out.println("-------------------");

                            new ItemPedidoBO().criar(itemPedido);
                            System.out.println("Item Pedido OK...");

                        }
                    }

                }
            }
            //Analise de Comissão
            Pessoa vendedorparaComissao = vendedor;
            if(vendedorparaComissao != null){
                if(vendedorparaComissao.getId() > 0){
                    ComissaoVendedor comissaoVendedor = new ComissaoVendedor();
                    comissaoVendedor.setDataComissao(Tempo.getDataAtualSql());
                    comissaoVendedor.setPessoa(vendedorparaComissao);
                    comissaoVendedor.setTotalComissao(carrinho.getValorTotal() * Comissao.comissaoporcentagem);
                    long idMax = new ComissaoVendedorBO().getLastId();

                    if (idMax == -1) {
                        comissaoVendedor.setId(1);
                    } else {
                        comissaoVendedor.setId(idMax + 1);
                    }
                    new ComissaoVendedorBO().criar(comissaoVendedor);
                    FacesMessages.info("Comissão Adquirida com sucesso!");
                }
            }

            FacesMessages.info("Compra Concluida com Sucesso!");

        } catch (Exception e) {
            System.out.println("ERRO EXCEPTION: " + e.getMessage());
            FacesMessages.error("Erro ao efetuar compra: " + e.getMessage());
        }

    }

    public void adicionarCupom() {
        if (!cupomAdicionado) {
            try {
                if (new CupomBO().getByName(valorCupom.toUpperCase())) {
                    valorTotalInicialCarrinho = itemCarrinho.getValor();
                    itemCarrinho.setValor((long) new CarrinhoBO()
                            .valorTotalComDesconto(2, itemCarrinhoList));
                    cupomAdicionado = true;
                    FacesMessages.info("Cupom adicionado!");
                } else {
                    FacesMessages.error("Cupom inválido!");
                }
            } catch (Exception e) {
                FacesMessages.error("Erro: " + e.getMessage());
            }
        } else {
            FacesMessages.error("Cupom ja foi adicionado!!");
        }
    }

    public void removerCupom() {
        if (cupomAdicionado) {
            itemCarrinho.setValor(valorTotalInicialCarrinho);
            cupomAdicionado = false;
            FacesMessages.info("Cupom removido!");
        } else {
            FacesMessages.error("Nenhum cupom adicionado!");
        }
    }

    public String removerItemDoCarrinho(ItemCarrinho itemCarrinho) {
        itemCarrinhoList.removeIf(itemCarrinho::equals);
        return RedirecionamentoController.irParaCarrinho();
    }

    public String valorTotalDoCarrinho() {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil(new CarrinhoBO()
                .valorTotalDoCarrinho(itemCarrinhoList));
    }

    public String valorTotalDebito() {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil(new CarrinhoBO()
                .valorTotalDebito(itemCarrinhoList));
    }

    public String valorTotalCredito() {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil(new CarrinhoBO()
                .valorTotalCredito(itemCarrinhoList));
    }

    public String valorTotalComFrete() {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil(new CarrinhoBO()
                .valorTotalComFrete(itemCarrinhoList));
    }

    public String valorTotalComDesconto() {
        return Moeda.converterNumeroParaDinheiroPadraoBrasil(new CarrinhoBO()
                .valorTotalComDesconto(2, itemCarrinhoList));
    }

    private String adicionarNoCarrinho(Produto produto) {

        pessoa = (Pessoa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pessoa");

        if (pessoa != null) {

            endereco.setCidade(pessoa.getEndereco().getCidade());
            endereco.setBairro(pessoa.getEndereco().getBairro());
            endereco.setCep(pessoa.getEndereco().getCep());
            endereco.setComplemento(pessoa.getEndereco().getComplemento());
            endereco.setNumero(pessoa.getEndereco().getNumero());
            endereco.setRua(pessoa.getEndereco().getRua());

            itemCarrinho = new ItemCarrinho();

            itemCarrinho.setProduto(produto);
            itemCarrinho.setCarrinho(carrinho);
            itemCarrinho.setQuantidade(quantidadeItem);
            itemCarrinho.setValor((long) produto.getPreco());

            itemCarrinhoList.add(itemCarrinho);

            return RedirecionamentoController.irParaCarrinho();
        } else {
            return RedirecionamentoController.irParaCadastro();
        }
    }

    public String adicionarProdutoNoCarrinho(Produto produto) {
        return adicionarNoCarrinho(produto);
    }

    public String adicionarProdutoNoCarrinho() {
        return adicionarNoCarrinho((Produto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("produto"));
    }

    public List<FormaPagamento> listarTodasFormasDePagamento() {
        try {
            return new FormaPagamentoBO().listarTodos();
        } catch (Exception e) {
            FacesMessages.error("Erro ao listar Forma de Pagamento: " + e.getMessage());
            return null;
        }
    }

//    public void cadastrarOuComprar() {
//        try {
//            if (endereco.getRua().equals(pessoa.getEndereco().getRua())
//                    && endereco.getNumero() == pessoa.getEndereco().getNumero()
//                    && endereco.getBairro().equals(pessoa.getEndereco().getBairro())
//                    && endereco.getCep().equals(pessoa.getEndereco().getCep())) {
//                endereco = pessoa.getEndereco();
//                //Enfin se tu olhar o xhtml o select deixa a cidade e estado null
//                //por isos essa verificação, Sim isso é uma puta gambiara n nego
//                //Mas se funciona Ta valendo.
//            } else if (endereco != pessoa.getEndereco()) {//caso o usuario escolha outro endereço
//                new EnderecoBO().criar(endereco);//endereço de Entrega
//            }
//        } catch (Exception e) {
//            FacesMessages.error("Erro ao cadastrar Endereco: " + e.getMessage());
//        }
//        pedido.setPessoa(pessoa);
//        pedido.setEndereco(endereco);
//        try {
//            new PedidoBO().criar(pedido);//cadastrar pacialmente pedido
//        } catch (Exception e) {
//            FacesMessages.error("Erro ao cadastrar Pedido: " + e.getMessage());
//        }
//        for (int x = 0; x <= itensCarrinhoList.size(); x++) {
//            try {
//                ItensPedido itensPedido = new ItensPedido();
//
//                itensPedido.setPedido(pedido);
//                itensPedido.setProduto(itensCarrinhoList.get(x).getProduto());
//                itensPedido.setQuantidade(itensCarrinhoList.get(x).getQuantidade());
//                itensPedido.setValor(itensCarrinhoList.get(x).getValor());
//
//                new ItensPedidoBO().criar(itensPedido);
//            } catch (Exception e) {
//                FacesMessages.error("Erro ao cadastrar ItensPedido: " + e.getMessage());
//            }
//            FacesMessages.info("ItensPedido Cadastrado com sucesso");
//        }
//        try {
//            pedido.setValorTotal(carrinho.getValorTotal());
//            pedido.setQuantidade(carrinho.getQuantidade());
//            new PedidoBO().alterar(pedido); //finalização de cadastro
//        } catch (Exception e) {
//            FacesMessages.error("Erro ao cadastrar Pedido: " + e.getMessage());
//        }
//        FacesMessages.info("Pedido Cadastrado com sucesso");
//    }
//
////    public String adicionarPreencherItensCarrinho() {
////
////        System.out.println("VAI TENTAR PEGAR O VALOR AGORA!");
////
////        List<ItensCarrinho> itensCarrinhos = (List<ItensCarrinho>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("carrinhoList");
////
////        if (itensCarrinhos == null) {
////            System.out.println("FICOU NULLO, VOU TACAR UM VALOR LA!");
////            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("carrinhoList",
////                    itensCarrinhoList);
////            System.out.println("TAQUEI!");
////        } else {
////            System.out.println("JA TENHO UM LIST AQUI, OLHA O TAMANHO DELE!!");
////            System.out.println("TAMANHO: " + itensCarrinhos.size());
////            System.out.println("MOSTREI!");
////        }
////
////        List<ItensCarrinho> itensCarrinhos1 = (List<ItensCarrinho>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("carrinhoList");
////
////        System.out.println("AINDA É NULL?" + itensCarrinhos1 == null);
////
////        //System.out.println("TAMANHO DO NOVO AO FINAL!!!!!!!!!: " + itensCarrinhos1.size());
////
//////        try {
//////            if (new ItensCarrinhoBO().valida(itemCarrinho)) {
//////                if (itensCarrinhos == null) {
//////                    System.out.println("ENTROU NO DA CLASSE (SESSÃO NULL)");
//////                    itensCarrinhoList.add(itemCarrinho);
//////                    //atualizarDadosCarrinho();
//////                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("carrinhoList",
//////                            itensCarrinhoList);
//////                } else {
//////                    System.out.println("ENTROU NO DA FUNCTION (SESSÃO JA TINHA ITENS)");
//////
//////                    System.out.println("VALOR DA LISTA LA DENTRO:" + itensCarrinhos.size());
//////
//////                    System.out.println("--------------------------------------------");
//////
//////                    for (ItensCarrinho i: itensCarrinhos) {
//////                        System.out.println("VALOR Q TAVA LA: " + i.getId());
//////                        System.out.println("VALOR Q TAVA LA: " + i.getQuantidade());
//////                        System.out.println("VALOR Q TAVA LA: " + i.getCarrinho().getId());
//////                        System.out.println("VALOR Q TAVA LA: " + i.getProduto().getId());
//////                        System.out.println("VALOR Q TAVA LA: " + i.getValor());
//////                    }
//////
//////                    System.out.println("--------------------------------------------");
//////
//////                    itensCarrinhos.add(itemCarrinho);
//////                    //atualizarDadosCarrinho();
//////                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("carrinhoList",
//////                            itensCarrinhos);
//////                }
//////                FacesMessages.info("Item adicionado ao carrinho!");
//////                return irParaCarrinho();
//////            }
//////        } catch (Exception e) {
//////            FacesMessages.error("Erro : " + e.getMessage());
//////        }
////
////        return "";
////    }

}
