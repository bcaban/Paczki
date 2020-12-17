package com.webappsbusters.parcelmanagement.service;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelStatus;
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

    //FIXME #1 in future we should introduce business-logic package with separate classes defining actions, for now it should be ok
    //FIXME #2 should we return Parcel or status update response or sth?
    public Optional<Parcel> updateParcelStatus(final String id, ParcelStatus status) {
        log.info("Updating parcel {} status to {}", id, status);

        final Optional<Parcel> maybeParcelToUpdate = getParcelById(id);
        return maybeParcelToUpdate.map(parcel -> updateParcelStatus(parcel, status));
    }

    private Parcel updateParcelStatus(Parcel parcel, ParcelStatus status) {
        parcel.setStatus(status);
        parcel.setTimeToDeliver(ParcelDeliveryTimeMock.mockTime(status));

        parcelRepository.save(parcel);

        return parcel;
    }
}
