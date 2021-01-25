package com.webappsbusters.parcelmanagement.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Duration;

@NoArgsConstructor
@Data
@ToString
public class UpdateParcelTimeToDeliverDto {
    Duration timeToDeliver;
}

