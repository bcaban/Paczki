package com.webappsbusters.parcelmanagement.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",  unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
    private String parcelId;

    @Column(name = "parcelStatus", columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    @Column(name = "senderCity", columnDefinition = "varchar(50)")
    private String senderCity;

    @Column(name = "senderPostCode", columnDefinition = "varchar(20)")
    private String senderPostCode;

    @Column(name = "senderStreet", columnDefinition = "varchar(50)")
    private String senderStreet;

    @Column(name = "receiverCity", columnDefinition = "varchar(50)")
    private String receiverCity;

    @Column(name = "receiverPostCode", columnDefinition = "varchar(20)")
    private String receiverPostCode;

    @Column(name = "receiverStreet", columnDefinition = "varchar(50)")
    private String receiverStreet;

    @Column(name = "weightInKg")
    private int weightInKg;

    @Column(name = "height")
    private int height;

    @Column(name = "length")
    private int length;

    @Column(name = "width")
    private int width;

    @Column(name = "size", columnDefinition = "varchar(3)")
    private String size;

    public static class ParcelBuilder {
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

        public ParcelBuilder status(ParcelStatus status) {
            this.status = status;
            return this;
        }

        public ParcelBuilder senderCity(String senderCity) {
            this.senderCity = senderCity;
            return this;
        }

        public ParcelBuilder senderPostCode(String senderPostCode) {
            this.senderPostCode = senderPostCode;
            return this;
        }

        public ParcelBuilder senderStreet(String senderStreet) {
            this.senderStreet = senderStreet;
            return this;
        }

        public ParcelBuilder receiverCity(String receiverCity) {
            this.receiverCity = receiverCity;
            return this;
        }

        public ParcelBuilder receiverPostCode(String receiverPostCode) {
            this.receiverPostCode = receiverPostCode;
            return this;
        }

        public ParcelBuilder receiverStreet(String receiverStreet) {
            this.receiverStreet = receiverStreet;
            return this;
        }

        public ParcelBuilder weightInKg(int weightInKg) {
            this.weightInKg = weightInKg;
            return this;
        }

        public ParcelBuilder height(int height) {
            this.height = height;
            return this;
        }

        public ParcelBuilder length(int length) {
            this.length = length;
            return this;
        }

        public ParcelBuilder width(int width) {
            this.width = width;
            return this;
        }

        public ParcelBuilder size(String size) {
            this.size = size;
            return this;
        }

        public Parcel build() {
            return new Parcel(status, senderCity, senderPostCode, senderStreet, receiverCity, receiverPostCode,
                    receiverStreet, weightInKg, height, length, width, size);
        }
    }

    private Parcel(ParcelStatus status, String senderCity, String senderPostCode, String senderStreet, String receiverCity,
                   String receiverPostCode, String receiverStreet, int weightInKg, int height, int length, int width, String size) {
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
    }
}
