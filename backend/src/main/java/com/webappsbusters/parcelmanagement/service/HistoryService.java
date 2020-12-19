package com.webappsbusters.parcelmanagement.service;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelHistories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class HistoryService {
    private final ParcelService parcelService;

    public HistoryService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    public Optional<ParcelHistories> getParcelHistoryById(final String id) {
        log.info("Searching for parcel history of id: {}", id);
        return parcelService.getParcelById(id).map(Parcel::getParcelHistories).map(ParcelHistories::new);
    }
}
