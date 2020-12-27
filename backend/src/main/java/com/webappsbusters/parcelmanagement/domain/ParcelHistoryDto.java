package com.webappsbusters.parcelmanagement.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@ToString
public class ParcelHistoryDto {
    private int id;
    private ParcelStatus status;
    private LocalDateTime timestamp;

}
