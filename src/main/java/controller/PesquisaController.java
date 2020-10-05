package controller;

import model.bo.ProdutoBO;
import model.dao.GenericDao;
import model.entities.Cliente;
import model.entities.Produto;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Named
@RequestScoped
public class PesquisaController implements Serializable {

    private List<Produto>todosProdutos;
    private List<Produto> produtosAtuais;
    private List<Integer> botoesPesquisa;

    public PesquisaController(){
        try {
             todosProdutos = new ProdutoBO().listarTodos();
             /* esse listar varia de acordo com os filtros escolhidos, EX: apenas Notebooks*/

             for (int x=0;x < 15 ; x++){//Lembrando o 15 é o total de produtos que serão mostrados por pag
                 produtosAtuais.set(x, todosProdutos.get(x));
             }//inicio start pag 1

             for(int x=0;x < 9; x++){//de acordo com o total de numero de botoes que vai ter na pag
                 botoesPesquisa.set(x,x);
             }
        } catch (Exception e) {
            System.out.println("Deu ruim chefia :(");
        }
        /*
        * Ao ir para pagina web de pesquisa ele já efetua o contrutor e já efetua o set dos valores ai
        *
        * Cada uma das Div de Produtos no site vai ter seu valores baseados nesses valores de produtoAtuais
        * */
    }
    public void MudarPag(int Numero_da_pag_Atual){
        for(int x=0;x<15;x++){
            produtosAtuais.set(x,todosProdutos.get(x + (15 * (Numero_da_pag_Atual - 1))));
            //atualização dos Produtos por pagina
        }

        //Mudar Botões
        int y = -4;
        for (int x=0;x<9;x++){
            botoesPesquisa.set(x,Numero_da_pag_Atual-y);
            y++;
            //atualiza os valores dos botoes da pag
        }
        /* set das paginas com base na pag atual, ai caso a pag seja 0 pra baixo no web deve ficar invisivel
        botoesPesquisa.set(0,Numero_da_pag_Atual-4);
        botoesPesquisa.set(1,Numero_da_pag_Atual-3);
        botoesPesquisa.set(2,Numero_da_pag_Atual-2);
        botoesPesquisa.set(3,Numero_da_pag_Atual-1);
        botoesPesquisa.set(4,Numero_da_pag_Atual);
        botoesPesquisa.set(5,Numero_da_pag_Atual+1);
        botoesPesquisa.set(6,Numero_da_pag_Atual+2);
        botoesPesquisa.set(7,Numero_da_pag_Atual+3);
        botoesPesquisa.set(8,Numero_da_pag_Atual+4);

         */
    }

}
