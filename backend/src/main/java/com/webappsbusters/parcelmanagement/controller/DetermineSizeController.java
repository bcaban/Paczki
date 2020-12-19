package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.*;
import com.webappsbusters.parcelmanagement.service.DetermineSize;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
public class DetermineSizeController {

    private final DetermineSize determineSize;
    private final MapperFacade mapperFacade;

    @Autowired
    public DetermineSizeController(DetermineSize determineSize, MapperFacade mapperFacade) {
        this.determineSize = determineSize;
        this.mapperFacade = mapperFacade;
    }

    @PostMapping("/dimensions")
    public ResponseEntity<ParcelSizeHolderDto> getDummyParcelDimensions(@RequestBody ParcelDimensionsDto parcelDimensionsDto) {
        int l = parcelDimensionsDto.getDummylength();
        int w = parcelDimensionsDto.getDummywidth();
        int h = parcelDimensionsDto.getDummyheight();
        return ResponseEntity.ok(new ParcelSizeHolderDto(mapperFacade.map(determineSize.DetermineDummyParcelSize(l,w,h), ParcelSizeDto.class)));
    }
}
