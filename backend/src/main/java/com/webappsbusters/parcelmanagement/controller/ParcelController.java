package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.ParcelDto;
import com.webappsbusters.parcelmanagement.mapper.ParcelMapper;
import com.webappsbusters.parcelmanagement.service.ParcelService;
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
    private final ParcelMapper parcelMapper;

    @Autowired
    public ParcelController(ParcelService parcelService, ParcelMapper parcelMapper) {
        this.parcelService = parcelService;
        this.parcelMapper = parcelMapper;
    }

    @GetMapping(value = "/parcels/{parcelId}")
    public ResponseEntity<ParcelDto> getParcel(@PathVariable String parcelId) {
        ParcelDto parcelDto =
                parcelMapper.mapToParcelDto(parcelService.getParcelById(parcelId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return new ResponseEntity<>(parcelDto, HttpStatus.FOUND);
    }
}
