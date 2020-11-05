package utilities;

import java.sql.Date;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Strings {

    public static String removerAcentosEspaco(String nomeArquivo) {
        return Normalizer
                .normalize(nomeArquivo, Normalizer.Form.NFD)
                // Caracteres especiais tipo >> ç á é ú õ //
                .replaceAll("[^\\p{ASCII}]", "")
                // Apenas Letras //
                .replaceAll("[^a-zA-Z]", "")
                // Espaços //
                .replaceAll("\\s+","");
    }

    public static String pegarResultadoAposPontoFinal(String nomeArquivo) {
        if (nomeArquivo.contains(".")) {
            return nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
        } else {
            return null;
        }
    }

    public static String formatarStringComDataAtual(String nomeArquivo) {
        Date agora = Date.valueOf(LocalDate.now());
        return new SimpleDateFormat("HH-mm-ss-SSS")
                .format(agora) + "-" + new SimpleDateFormat("yyyy-MM-dd")
                .format(agora) + "-" + nomeArquivo;
    }

}
