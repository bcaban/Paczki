package com.webappsbusters.parcelmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "parcels")
@Builder
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

    @Column(name = "size", columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private ParcelSize size;

    @Column(name = "timeToDeliver")
    private Duration timeToDeliver;

    @Column(name = "name")
    private String name;

    @OneToMany(
            targetEntity = ParcelHistory.class,
            mappedBy = "parcelId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ParcelHistory> parcelHistories = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parcelAccesses_id", referencedColumnName = "id")
    private ParcelAccess parcelAccess; //TODO PAC-42 generate this on parcel create

    public void setStatus(ParcelStatus newStatus) {
        if (Objects.nonNull(status) && !status.canChangeTo(newStatus)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Cannot change parcel status from %s to %s", status, newStatus));
        }

        this.status = newStatus;
    }
}