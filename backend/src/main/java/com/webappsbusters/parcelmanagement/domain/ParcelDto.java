package com.webappsbusters.parcelmanagement.domain;

import lombok.*;

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
    private String size;
    private Duration timeToDeliver;

    @Builder
    public static class ParcelDtoBuilder {
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
        private String size;
        private Duration timeToDeliver;
    }
}
