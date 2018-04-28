package br.com.pmgt.uteis;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = DateConverter.ID)
public class DateConverter extends DateTimeConverter {

	public static final String ID = "br.com.pmgt.uteis.DateConverter";

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		Object object = super.getAsObject(facesContext, uiComponent, value);
		if (object instanceof Date) {
			return (Date) object;
		} else {
			throw new IllegalArgumentException(String
					.format("value=%s Não foi possível converter Date, resultado super.getAsObject=%s", value, object));
		}

	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value == null)
			return super.getAsString(facesContext, uiComponent, value);
		if (value instanceof Date) {
			Date date = (Date) value;
			return super.getAsString(facesContext, uiComponent, date);
		} else {
			throw new IllegalArgumentException(String.format("value=%s não é um Date", value));
		}

	}
}