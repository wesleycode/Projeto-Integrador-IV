package utilities;

import java.text.NumberFormat;
import java.util.Locale;

public class Moeda {

    public static String converterLongParaDinheiroStringPadraoBrasil(long l) {
        return NumberFormat.getCurrencyInstance(new Locale("pr","BR")).format(l);
    }

    public static String converterLongParaDinheiroStringPadraoEua(long l) {
        return NumberFormat.getCurrencyInstance(new Locale("en","US")).format(l);
    }

}
