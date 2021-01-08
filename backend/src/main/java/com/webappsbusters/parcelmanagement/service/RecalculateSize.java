package com.webappsbusters.parcelmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class RecalculateSize {
    /*
    private final ParcelRepository parcelRepository;

    @Autowired
    public RecalculateSize(ParcelRepository parcelRepository) { this.parcelRepository = parcelRepository; }

    public Optional<Parcel> getParcelById(final String id) {
        log.info("Searching for parcel of id: {}", id);
        return parcelRepository.findById(id);
    }
    public Optional<Parcel> updateParcelSize(final String id, ParcelSize size) {
        log.info("Updating parcel {} Size to {}", id, size);

        final Optional<Parcel> maybeParcelToUpdate = getParcelById(id);
        return maybeParcelToUpdate.map(parcel -> updateParcelSize(parcel, size));
    }
    private Parcel updateParcelSize(Parcel parcel, ParcelSize size) {
        ParcelDimensions pd = new ParcelDimensions();
        int l = pd.l(parcel);
        int w = pd.w(parcel);
        int h = pd.h(parcel);

        if (l < 0 || w < 0 || h < 0) {
            SML = "Wprwadzono nieprawidłowe wymiary paczki.";
        }
        if (l > 0 && w > 0 && h > 0){
            if (l > 60 || w > 60 || h > 60) {
                parcel.setSize(ParcelSize.Large);
            }
            else if (l <= 30 && w <= 30 && h <= 30){
                parcel.setSize(ParcelSize.Small);
            }
            else {
                parcel.setSize(ParcelSize.Medium);
            }
        }
        parcelRepository.save(parcel);
        return parcel;
    }
    */
}
