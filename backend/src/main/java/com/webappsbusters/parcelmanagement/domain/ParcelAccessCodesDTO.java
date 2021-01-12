package com.webappsbusters.parcelmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelAccessCodesDTO {
    String clientCode;
    String adminCode;
}
