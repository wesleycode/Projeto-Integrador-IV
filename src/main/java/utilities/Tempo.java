package utilities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Tempo {

    public static Date converterUtilDateParaSqlDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static java.util.Date converterSqlDateParaUtilDate(Date date) {
        return new java.util.Date(date.getTime());
    }

    public static java.util.Date converterStringParaUtilDate(String data) throws Exception {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (ParseException e) {
            throw new Exception("Erro ao converter String para UTIL date: " + e.getMessage());
        }
    }

    public static String converterDateParaString(Date data) throws Exception {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").format(data);
        } catch (Exception e) {
            throw new Exception("Erro ao converter Date para String: " + e.getMessage());
        }
    }

    public static Timestamp converterDateParaTimestamp(Date data) {
        return new Timestamp(data.getTime());
    }

    public static Date converterTimestampParaDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }


    public static Date converterStringParaSqlDate(String data) throws Exception {
        try {
            return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(data).getTime());
        } catch (ParseException e) {
            throw new Exception("Erro ao converter String para SQL date: " + e.getMessage());
        }
    }

    public static Date getDataAtualSql() {
        return Date.valueOf(LocalDate.now());
    }

    public static Date getDataAtualSql(int dia, int mes, int ano) {
        return Date.valueOf(LocalDate.of(ano,mes,dia));
    }

    public static Time getHoraAtualSql() {
        return Time.valueOf(LocalTime.now());
    }

    public static String getHoraAtualString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String getHoraAtualStringFormatado(String formato) {
        // Exemplo "HH:mm:ss" HH > Horas, mm > Minutos, ss > Segundos //
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));
    }

    public static String getDataAtualStringFormatado(String formato) {
        // Exemplo "dd-MM-yyyy" dd > Dia, MM > Mes, yyyy > Ano //
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));
    }

    public static Date adicionarOuRemoverDiaSqlDate(Date data, int dias, boolean adicionar) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        if (adicionar) {
            c.add(Calendar.DATE, dias);
        } else {
            c.add(Calendar.DATE, -dias);
        }
        return new Date(c.getTimeInMillis());
    }

    public static Time adicionarOuRemoverHorasSqlTime(Time time, int horas, boolean adicionar) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        if (adicionar) {
            c.add(Calendar.HOUR, horas);
        } else {
            c.add(Calendar.HOUR, -horas);
        }
        return new Time(c.getTimeInMillis());
    }

    public static Time adicionarOuRemoverSegundosSqlTime(Time time, int segundos, boolean adicionar) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        if (adicionar) {
            c.add(Calendar.SECOND, segundos);
        } else {
            c.add(Calendar.SECOND, -segundos);
        }
        return new Time(c.getTimeInMillis());
    }

    public static Date adicionarOuRemoverMesesSqlTime(Date date, int meses, boolean adicionar) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (adicionar) {
            c.add(Calendar.MONTH, meses);
        } else {
            c.add(Calendar.MONTH, -meses);
        }
        return new Date(c.getTimeInMillis());
    }

}
