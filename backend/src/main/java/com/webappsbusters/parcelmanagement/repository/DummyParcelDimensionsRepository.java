package com.webappsbusters.parcelmanagement.repository;

import com.webappsbusters.parcelmanagement.domain.ParcelDimensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyParcelDimensionsRepository extends JpaRepository<ParcelDimensions, String> {
}

