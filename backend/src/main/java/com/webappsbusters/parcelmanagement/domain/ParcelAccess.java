package com.webappsbusters.parcelmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "parcelAccesses")
public class ParcelAccess {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
    String id;

    @OneToOne()
    private Parcel parcelId;

    @Column(name = "clientCode", nullable = false, columnDefinition = "VARCHAR(36)")
    String clientCode;

    @Column(name = "adminCode", nullable = false, columnDefinition = "VARCHAR(36)")
    String adminCode;
}
