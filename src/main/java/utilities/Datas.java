package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Datas {

    public static java.sql.Date converterUtilDateParaSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date converterSqlDateParaUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

    public static java.util.Date converterStringParaUtilDate(String data) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static java.sql.Date converterStringParaSqlDate(String data) {
        try {
            return new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(data).getTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
