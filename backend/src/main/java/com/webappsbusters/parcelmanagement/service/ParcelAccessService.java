package com.webappsbusters.parcelmanagement.service;

import com.webappsbusters.parcelmanagement.domain.*;
import com.webappsbusters.parcelmanagement.repository.ParcelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ParcelAccessService {
    private final ParcelService parcelService;
    private final ParcelRepository parcelRepository;

    public Optional<ParcelAccessStatus> checkAccess(String parcelId, ParcelAccessCodes parcelAccessCodes) {
        log.info("Checking access status of parcel {}", parcelId);
        return parcelService.getParcelById(parcelId)
                .map(parcel -> checkAccess(parcel, parcelAccessCodes));
    }

    private ParcelAccessStatus checkAccess(Parcel parcel, ParcelAccessCodes parcelAccessCodes) {
        if (Objects.isNull(parcel.getParcelAccess())) {
            return ParcelAccessStatus.DENIED;
        }

        if (Objects.nonNull(parcelAccessCodes.getAdminCode())) {
            return parcelAccessCodes.getAdminCode().equals(parcel.getParcelAccess().getAdminCode()) ?
                    ParcelAccessStatus.GRANTED : ParcelAccessStatus.DENIED;
        }

        if (Objects.nonNull(parcelAccessCodes.getClientCode())) {
            return parcelAccessCodes.getClientCode().equals(parcel.getParcelAccess().getClientCode()) ?
                    ParcelAccessStatus.GRANTED : ParcelAccessStatus.DENIED;
        }

        return ParcelAccessStatus.DENIED;
    }

    public Optional<Parcel> updateParcelClientCode(String parcelId) {
        String clientCode = UUID.randomUUID().toString();

        return parcelService.getParcelById(parcelId)
                .map(parcel -> {
                    parcel.getParcelAccess().setClientCode(clientCode);
                    parcelRepository.save(parcel);
                    return parcel;
                });
    }
}
