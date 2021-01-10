package com.webappsbusters.parcelmanagement.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ChangeDeliveryAddressDto {
    private String receiverCity;
    private String receiverPostCode;
    private String receiverStreet;
}
