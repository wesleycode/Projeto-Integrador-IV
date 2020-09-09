package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
import java.sql.Date;

public class DataHora {

    public static Date getCurrentDate() {
        try {
            return new Date(new java.util.Date().getTime());
        } catch (Exception e) {
            System.out.println("Não foi possivel pegar a data do sistema");
            return null;
        }
    }

    public static Time stringToTime(String texto) {
        try {
            if (texto.length() == 4) {
                texto = "0" + texto;
            }
            if (Texto.isHorario(texto)) {
                return Time.valueOf(LocalTime.parse(texto));
            } else {
                throw new Exception("Horário Inválido!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Time getCurrentTime() {
        try {
            return new Time(new java.util.Date().getTime());
        } catch (Exception e) {
            System.out.println("Não foi possivel pegar horario do sistema");
            return null;
        }
    }

    public static Timestamp stringToLapTime(String time) {
        try {
            return new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                    .parse(LocalDate.now() + " 00:" + time)
                    .getTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String lapTimeToString(Timestamp time) {
        try {
            return new SimpleDateFormat("mm:ss.SSS").format(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Date stringToSqlDate(String data) {
        try {
            return Date.valueOf(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (Exception e) {
            return Date.valueOf(LocalDate.parse("01/01/1000", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    public static String dateToPadraoBrasil(Date data) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").format(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String dateToPadraoEua(Date data) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}