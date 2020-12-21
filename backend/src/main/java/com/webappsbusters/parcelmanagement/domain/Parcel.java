package com.webappsbusters.parcelmanagement.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;

@NoArgsConstructor
@Data
@Entity(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
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

    @Column(name = "timeToDeliver")
    private Duration timeToDeliver;

    @Builder
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
        private Duration timeToDeliver;
    }
}