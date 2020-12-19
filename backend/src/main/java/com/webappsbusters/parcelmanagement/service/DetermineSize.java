package com.webappsbusters.parcelmanagement.service;
import com.webappsbusters.parcelmanagement.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DetermineSize {
    public ParcelSize DetermineDummyParcelSize(int l, int w, int h) {
        if (l > 0 && w > 0 && h > 0) {
            if (l <= 30 && w <= 30 && h <= 30) {
                //dummyParcel.setSize(ParcelSize.Small);
                return ParcelSize.Small;
            } else {
                //dummyParcel.setSize(ParcelSize.Medium);
                return ParcelSize.Medium;
            }
        }
        return ParcelSize.Large;
    }
}
