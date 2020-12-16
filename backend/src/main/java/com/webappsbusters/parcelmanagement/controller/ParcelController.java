package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.ParcelDto;
import com.webappsbusters.parcelmanagement.mapper.ParcelMapper;
import com.webappsbusters.parcelmanagement.service.ParcelService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/parcels")
public class ParcelController {

    private final ParcelService parcelService;
    private final MapperFacade mapperFacade;

    @Autowired
    public ParcelController(ParcelService parcelService, MapperFacade mapperFacade) {
        this.parcelService = parcelService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping(value = "/{parcelId}")
    public ResponseEntity<ParcelDto> getParcel(@PathVariable String parcelId) {
        ParcelDto parcelDto =
                mapperFacade.map(parcelService.getParcelById(parcelId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), ParcelDto.class);
        return ResponseEntity.ok(parcelDto);
    }

    @PutMapping(value = "/{parcelId}/status")
    public ResponseEntity<ParcelDto> updateParcelStatus(@PathVariable String parcelId) {
        ParcelDto parcelDto =
                parcelMapper.mapToParcelDto(parcelService.getParcelById(parcelId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return ResponseEntity.ok(parcelDto);
    }
}
