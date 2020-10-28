package model.converters;

import model.entities.Categoria;
import net.bootsfaces.utils.FacesMessages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Map;

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return getMap(component).get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Categoria categoria = (Categoria) value;
            addValue(component, categoria);
            return String.valueOf(categoria.getId());
        }
        return null;
    }

    private Map<String, Object> getMap(UIComponent component) {
        return component.getAttributes();
    }

    private void addValue(UIComponent component, Categoria categoria) {
        getMap(component).put(String.valueOf(categoria.getId()), categoria);
    }

}

