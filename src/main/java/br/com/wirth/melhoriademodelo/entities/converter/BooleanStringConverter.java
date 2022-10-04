package br.com.wirth.melhoriademodelo.entities.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if (aBoolean != null) {
            return aBoolean ? "S" : "N";
        } else {
            return "?";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s != null){
            return s.equals("S");
        } else {
            return false;
        }
    }

}
