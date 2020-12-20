package com.webappsbusters.parcelmanagement.service;
import com.webappsbusters.parcelmanagement.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DetermineSize {
    public ParcelSize DetermineDummyParcelSize(int length, int width, int height) {
        if (length > 0 && width > 0 && height > 0) {
            if (length > 60 || width > 60 || height > 60) {
                return ParcelSize.Large;
            }
            if (length <= 30 && width <= 30 && height <= 30) {
                return ParcelSize.Small;
            } else {
                return ParcelSize.Medium;
            }
        } else {
            return ParcelSize.None;
        }
    }
}
