package utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Moeda {

    public static <T> String converterNumeroParaDinheiroPadraoEua(T d) {
        return new DecimalFormat("¤ ###,###,##0.00",
                new DecimalFormatSymbols(Locale.US)).format(d);
    }

    public static <T> String converterNumeroParaDinheiroPadraoGermany(T d) {
        return new DecimalFormat("¤ ###,###,##0.00",
                new DecimalFormatSymbols(Locale.GERMANY)).format(d);
    }

    public static <T> String converterNumeroParaDinheiroPadraoBrasil(T d) {
        return new DecimalFormat("¤ ###,###,##0.00",
                new DecimalFormatSymbols(new Locale("pt",
                        "BR"))).format(d);
    }

}
