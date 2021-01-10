package com.webappsbusters.parcelmanagement.service;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelHistory;
import com.webappsbusters.parcelmanagement.domain.ParcelStatus;
import com.webappsbusters.parcelmanagement.repository.ParcelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    //FIXME #1 in future we should introduce business-logic package with separate classes defining actions, for now
    // it should be ok
    //FIXME #2 should we return Parcel or status update response or sth?
    public Optional<Parcel> updateParcelStatus(final String id, ParcelStatus status) {
        log.info("Updating parcel {} status to {}", id, status);

        final Optional<Parcel> maybeParcelToUpdate = getParcelById(id);
        return maybeParcelToUpdate.map(parcel -> updateParcelStatus(parcel, status));
    }

    private Parcel updateParcelStatus(Parcel parcel, ParcelStatus status) {
        parcel.setStatus(status);
        parcel.setTimeToDeliver(ParcelDeliveryTimeMock.mockTime(status));

        parcel.getParcelHistories().add(ParcelHistory.builder().parcelId(parcel).status(status).timestamp(LocalDateTime.now()).build());

        parcelRepository.save(parcel);

        return parcel;
    }

    public void saveParcel(Parcel parcel) {
        parcelRepository.save(parcel);
    }

    public Optional<Parcel> changeDeliveryAddress(String parcelId, String receiverCity, String receiverPostCode,
                                                  String receiverStreet) {

        return getParcelById(parcelId)
                .filter(parcel -> parcel.getStatus().equals(ParcelStatus.ID_ADDED))
                .map(parcel -> {
                    parcel.setReceiverCity(receiverCity);
                    parcel.setReceiverPostCode(receiverPostCode);
                    parcel.setReceiverStreet(receiverStreet);
                    parcelRepository.save(parcel);
                    return parcel;
                });
    }

    public Optional<Parcel> changeName(String parcelId, String name) {
        return getParcelById(parcelId)
                .map(parcel -> {
                    parcel.setName(name);
                    parcelRepository.save(parcel);
                    return parcel;
                });
    }
}
