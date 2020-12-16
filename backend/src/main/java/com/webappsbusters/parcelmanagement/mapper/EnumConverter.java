package com.webappsbusters.parcelmanagement.mapper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class EnumConverter extends CustomConverter<Enum, Enum> {

    @Override
    public boolean canConvert(Type<?> sourceType, Type<?> destinationType) {
        return sourceType.isEnum() && destinationType.isEnum();
    }

    @Override
    public Enum convert(Enum source, Type<? extends Enum> destinationType, MappingContext context) {
        try {
            return Enum.valueOf(destinationType.getRawType(), source.name());
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }
}
