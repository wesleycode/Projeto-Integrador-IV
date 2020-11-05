package model.bo;

import javax.servlet.http.Part;

public class ArquivoBO {

    public static boolean validarImagem(Part o) throws Exception {
        if (o.getName().equals("")) {
            throw new Exception("Nome da foto Null");
        }
        if (o.getSize() >= 10000000) {
            throw new Exception("Imagem grande demais, adicione uma imagem MENOR que 10Mbs");
        }
        return true;
    }

}