package utils;

import java.util.Date;
import java.util.regex.Pattern;

public class Texto {

    // Expressões regulares //

    // \\d Todos os digitos
    // \\D Tudo que não for digito
    // \\s Espaços em branco \t \n \f \r
    // \\S Caractére não branco
    // \\w Caractére de palavras [a-z] [A-Z] [0-9] e [_]
    // \\W Tudo que não for caractére de palavra

    public static boolean isApenasLetrasMaiusculas(String texto) {
        return Pattern.compile("[A-Z]").matcher(texto).find();
    }

    public static boolean isApenasLetrasMinusculas(String texto) {
        return Pattern.compile("[a-z]").matcher(texto).find();
    }

    public static boolean isApenasNumeros(String texto) {
        return Pattern.compile("[0-9]").matcher(texto).find();
    }

    public static boolean isHorario(String texto) {
        return Pattern.compile("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$").matcher(texto).find();
    }

    public static boolean isDataPadraoEUA(String texto) {
        return Pattern.compile("^(19|20)\\d\\d[--.](0[1-9]|1[012])[--.](0[1-9]|[12][0-9]|3[01])").matcher(texto).find();
    }

    public static boolean isDataPadraoBRA(String texto) {
        return Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d").matcher(texto).find();
    }

    public static boolean isApenasLetras(String texto) {
        return Pattern.compile("[a-zA-Z]").matcher(texto).find();
    }

    public static boolean isApenasLetrasOuNumeros(String texto) {
        return Pattern.compile("[a-zA-Z0-9]").matcher(texto).find();
    }

    public static boolean isEmail(String texto) {
        return Pattern.compile("([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)").matcher(texto).find();
    }

    public static boolean isEspacosExistentes(String texto) {
        return Pattern.compile("\\s").matcher(texto).find();
    }

}
