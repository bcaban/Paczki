package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.*;
import com.webappsbusters.parcelmanagement.service.ParcelService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
public class ParcelController {

    private final ParcelService parcelService;
    private final MapperFacade mapperFacade;

    @Autowired
    public ParcelController(ParcelService parcelService, MapperFacade mapperFacade) {
        this.parcelService = parcelService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping(value = "/parcels/{parcelId}")
    public ResponseEntity<ParcelDto> getParcel(@PathVariable String parcelId) {
        Parcel parcel = parcelService.getParcelById(parcelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ParcelDto parcelDto = mapperFacade.map(parcel, ParcelDto.class);

        return ResponseEntity.ok(parcelDto);
    }

    @PutMapping("/parcels/{parcelId}/status")
    public ResponseEntity<UpdateParcelStatusDto> updateStatus(@PathVariable String parcelId, @RequestBody UpdateParcelStatusDto updateParcelStatus) {
        ParcelStatus newStatus = mapperFacade.map(updateParcelStatus.getStatus(), ParcelStatus.class);

        parcelService.updateParcelStatus(parcelId, newStatus)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(updateParcelStatus);
    }
}
