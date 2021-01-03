package com.webappsbusters.parcelmanagement.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;

@NoArgsConstructor
@Data
@ToString
public class ParcelDto {
    private String parcelId;
    private ParcelStatusDto status;
    private String senderCity;
    private String senderPostCode;
    private String senderStreet;
    private String receiverCity;
    private String receiverPostCode;
    private String receiverStreet;
    private int weightInKg;
    private int height;
    private int length;
    private int width;
    private ParcelSizeDto size;
    private Duration timeToDeliver;
    private String name;

    @Builder
    public static class ParcelDtoBuilder {
        private final String parcelId;
        private final ParcelStatusDto status;
        private final String senderCity;
        private final String senderPostCode;
        private final String senderStreet;
        private final String receiverCity;
        private final String receiverPostCode;
        private final String receiverStreet;
        private final int weightInKg;
        private final int height;
        private final int length;
        private final int width;
        private final ParcelSizeDto size;
        private final Duration timeToDeliver;
        private final String name;
    }
}
