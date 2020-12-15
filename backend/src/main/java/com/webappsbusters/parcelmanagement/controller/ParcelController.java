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
@RequestMapping("/v1")
public class ParcelController {

    private final ParcelService parcelService;
    private final ParcelMapper parcelMapper;
    private final MapperFacade mapperFacade;

    @Autowired
    public ParcelController(ParcelService parcelService, ParcelMapper parcelMapper, MapperFacade mapperFacade) {
        this.parcelService = parcelService;
        this.parcelMapper = parcelMapper;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping(value = "/parcels/{parcelId}")
    public ResponseEntity<ParcelDto> getParcel(@PathVariable String parcelId) {
        ParcelDto parcelDto =
                mapperFacade.map(parcelService.getParcelById(parcelId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),ParcelDto.class);
        return ResponseEntity.ok(parcelDto);
    }
}
