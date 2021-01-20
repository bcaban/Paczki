package com.webappsbusters.parcelmanagement.repository;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, String> {
    Optional<Parcel> findByNameAndParcelAccessClientCode(String name, String clientAccessCode);
}
