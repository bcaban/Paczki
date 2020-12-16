package com.webappsbusters.parcelmanagement.domain;

import lombok.*;

import java.time.Duration;

@NoArgsConstructor
@Data
@ToString
public class ParcelDto {
    private String parcelId;
    private ParcelStatus status;
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

    public static class ParcelDtoBuilder {
        private String parcelId;
        private ParcelStatus status;
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

        public ParcelDtoBuilder parcelId(String parcelId) {
            this.parcelId = parcelId;
            return this;
        }

        public ParcelDtoBuilder status(ParcelStatus status) {
            this.status = status;
            return this;
        }

        public ParcelDtoBuilder senderCity(String senderCity) {
            this.senderCity = senderCity;
            return this;
        }

        public ParcelDtoBuilder senderPostCode(String senderPostCode) {
            this.senderPostCode = senderPostCode;
            return this;
        }

        public ParcelDtoBuilder senderStreet(String senderStreet) {
            this.senderStreet = senderStreet;
            return this;
        }

        public ParcelDtoBuilder receiverCity(String receiverCity) {
            this.receiverCity = receiverCity;
            return this;
        }

        public ParcelDtoBuilder receiverPostCode(String receiverPostCode) {
            this.receiverPostCode = receiverPostCode;
            return this;
        }

        public ParcelDtoBuilder receiverStreet(String receiverStreet) {
            this.receiverStreet = receiverStreet;
            return this;
        }

        public ParcelDtoBuilder weightInKg(int weightInKg) {
            this.weightInKg = weightInKg;
            return this;
        }

        public ParcelDtoBuilder height(int height) {
            this.height = height;
            return this;
        }

        public ParcelDtoBuilder length(int length) {
            this.length = length;
            return this;
        }

        public ParcelDtoBuilder width(int width) {
            this.width = width;
            return this;
        }

        public ParcelDtoBuilder size(String size) {
            this.size = size;
            return this;
        }

        public ParcelDtoBuilder timeToDeliver(Duration timeToDeliver) {
            this.timeToDeliver = timeToDeliver;
            return this;
        }

        public ParcelDto build() {
            return new ParcelDto(parcelId, status, senderCity, senderPostCode, senderStreet, receiverCity,
                    receiverPostCode, receiverStreet, weightInKg, height, length, width, size, timeToDeliver);
        }

    }

    private ParcelDto(String parcelId, ParcelStatus status, String senderCity, String senderPostCode, String senderStreet, String receiverCity,
                      String receiverPostCode, String receiverStreet, int weightInKg, int height, int length, int width, String size, Duration timeToDeliver) {
        this.parcelId = parcelId;
        this.status = status;
        this.senderCity = senderCity;
        this.senderPostCode = senderPostCode;
        this.senderStreet = senderStreet;
        this.receiverCity = receiverCity;
        this.receiverPostCode = receiverPostCode;
        this.receiverStreet = receiverStreet;
        this.weightInKg = weightInKg;
        this.height = height;
        this.length = length;
        this.width = width;
        this.size = size;
        this.timeToDeliver = timeToDeliver;
    }
}
