package com.webappsbusters.parcelmanagement.mapper;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelDto;
import org.springframework.stereotype.Component;

@Component
public class ParcelMapper {

    public Parcel mapToParcel(final ParcelDto parcelDto) {
        return new Parcel.ParcelBuilder()
                .status(parcelDto.getStatus())
                .senderCity(parcelDto.getSenderCity())
                .senderPostCode(parcelDto.getSenderPostCode())
                .senderStreet(parcelDto.getSenderStreet())
                .receiverCity(parcelDto.getReceiverCity())
                .receiverPostCode(parcelDto.getReceiverPostCode())
                .receiverStreet(parcelDto.getReceiverStreet())
                .weightInKg(parcelDto.getWeightInKg())
                .height(parcelDto.getHeight())
                .length(parcelDto.getLength())
                .width(parcelDto.getWidth())
                .size(parcelDto.getSize())
                .build();
    }

    public ParcelDto mapToParcelDto (final Parcel parcel) {
        return new ParcelDto.ParcelDtoBuilder()
                .parcelId(parcel.getParcelId())
                .status(parcel.getStatus())
                .senderCity(parcel.getSenderCity())
                .senderPostCode(parcel.getSenderPostCode())
                .senderStreet(parcel.getSenderStreet())
                .receiverCity(parcel.getReceiverCity())
                .receiverPostCode(parcel.getReceiverPostCode())
                .receiverStreet(parcel.getReceiverStreet())
                .weightInKg(parcel.getWeightInKg())
                .height(parcel.getHeight())
                .length(parcel.getLength())
                .width(parcel.getWidth())
                .size(parcel.getSize())
                .build();

    }
}
