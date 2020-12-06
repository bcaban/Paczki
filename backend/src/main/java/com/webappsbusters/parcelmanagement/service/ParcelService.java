package com.webappsbusters.parcelmanagement.service;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.repository.ParcelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ParcelService {
    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public Optional<Parcel> getParcelById(final String id) {
        log.info("Searching for parcel of id: {}", id);
        return parcelRepository.findById(id);
    }
}
