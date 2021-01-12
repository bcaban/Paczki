package com.webappsbusters.parcelmanagement.service;

import com.webappsbusters.parcelmanagement.domain.ParcelStatus;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class ParcelDeliveryTimeMock {

    public Duration mockTime(ParcelStatus status) {
        switch (status) {
            case ID_ADDED:
                return Duration.ofDays(ThreadLocalRandom.current().nextInt(1, 3));
            case IN_TRANSIT:
                return Duration.ofDays(ThreadLocalRandom.current().nextInt(1, 2));
            case OUT_FOR_DELIVERY:
            case DELIVERED:
                return Duration.ofDays(0);
            case MISSING_IN_ACTION:
                return Duration.ofDays(14);
        }

        return Duration.ofDays(0);
    }
}
