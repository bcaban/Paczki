package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.Parcel;
import com.webappsbusters.parcelmanagement.domain.ParcelDto;
import com.webappsbusters.parcelmanagement.service.ParcelService;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class ParcelByNameController {

    private final ParcelService parcelService;
    private final MapperFacade mapperFacade;

    @GetMapping("/parcels")
    public ResponseEntity<ParcelDto> findParcelByName(@RequestParam String name, @RequestParam String accessCode) {
        Parcel parcel = parcelService.getParcelByNameAndAccessCode(name, accessCode).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ParcelDto parcelDto = mapperFacade.map(parcel, ParcelDto.class);

        return ResponseEntity.ok(parcelDto);
    }
}
