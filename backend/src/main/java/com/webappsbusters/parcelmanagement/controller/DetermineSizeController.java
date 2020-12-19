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

    @GetMapping(value = "/dimensions")
    public ResponseEntity<ParcelDimensionsDto> getDummyParcelDimensions(int parcel_width, int parcel_length, int parcel_height) {
        ParcelDimensions dummyParcelDimensions = determineSize.getDummyParcelDimensions().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    
        ParcelDimensionsDto dummyParcelDimensionsDto = mapperFacade.map(dummyParcelDimensions, ParcelDimensionsDto.class);
    
        return ResponseEntity.ok(dummyParcelDimensionsDto);
    }

    @PostMapping("/dimensions/done")
    public ResponseEntity<UpdateParcelSizeDto> updateSize(@PathVariable String parcelId, @RequestBody UpdateParcelSizeDto updateParcelSize) {
        ParcelSize newSize = mapperFacade.map(updateParcelSize.getSize(), ParcelSize.class);

        DetermineSize.updateParcelSize(parcelId, newSize)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(updateParcelSize);
    }
}
