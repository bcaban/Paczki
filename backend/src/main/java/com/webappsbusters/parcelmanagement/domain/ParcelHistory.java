package com.webappsbusters.parcelmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "history")
@Builder
public class ParcelHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "parcelStatus", columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    @Column(name = "timestamp", columnDefinition = "varchar(1000)")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "parcelId")
    private Parcel parcelId;

}
