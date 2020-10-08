package model.converters;

import net.bootsfaces.utils.FacesMessages;
import utilities.Tempo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.sql.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Tempo.converterStringParaSqlDate(value);
        } catch (Exception e) {
            FacesMessages.error("Não foi possivel converter a data (OBJECT): " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            return Tempo.converterDateParaString((Date) value);
        } catch (Exception e) {
            FacesMessages.error("Não foi possivel converter a data (STRING): " + e.getMessage());
        }
        return null;
    }

}

