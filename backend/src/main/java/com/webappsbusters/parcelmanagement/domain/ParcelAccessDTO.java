package com.webappsbusters.parcelmanagement.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ParcelAccessDTO {
    private String id;
    private String clientCode;
    private String adminCode;
}
