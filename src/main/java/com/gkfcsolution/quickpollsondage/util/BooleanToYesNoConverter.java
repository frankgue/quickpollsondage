package com.gkfcsolution.quickpollsondage.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToYesNoConverter  implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null) ? "yes" : "no";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "yes".equals(dbData);
    }
}
