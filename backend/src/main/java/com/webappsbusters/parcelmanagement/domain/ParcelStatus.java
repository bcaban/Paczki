package com.webappsbusters.parcelmanagement.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public enum ParcelStatus {
    CANCELLED (List.of()),
    DELIVERED (List.of()),
    MISSING_IN_ACTION (List.of(CANCELLED, DELIVERED)),
    OUT_FOR_DELIVERY (List.of(DELIVERED, MISSING_IN_ACTION, CANCELLED)),
    IN_TRANSIT (List.of(OUT_FOR_DELIVERY, DELIVERED, MISSING_IN_ACTION, CANCELLED)),
    ID_ADDED (List.of(IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, MISSING_IN_ACTION, CANCELLED));

    private final List<ParcelStatus> possibleNextStatuses;

    public boolean canChangeTo(ParcelStatus newStatus) {
        return possibleNextStatuses.contains(newStatus);
    }
}
