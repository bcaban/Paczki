package com.webappsbusters.parcelmanagement;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class OrikaAttempt extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Parcel.class, ParcelDto.class)
                .byDefault()
                .register();
        factory.classMap(size.class, sizeDto.class)
                .byDefault()
                .register();
    }
}
