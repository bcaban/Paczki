package com.webappsbusters.parcelmanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
public class RecalculateSizeController {
/*
    private final RecalculateSize recalculateSize;
    private final MapperFacade mapperFacade;

    @Autowired
    public RecalculateSizeController(RecalculateSize recalculateSize, MapperFacade mapperFacade) {
        this.recalculateSize = recalculateSize;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping(value = "/parcels/{parcelId}")
    public ResponseEntity<ParcelDto> getParcel(@PathVariable String parcelId) {
        Parcel parcel = recalculateSize.getParcelById(parcelId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ParcelDto parcelDto = mapperFacade.map(parcel, ParcelDto.class);

        return ResponseEntity.ok(parcelDto);
    }

    @PostMapping("/parcels/{parcelId}/size")
    public ResponseEntity<UpdateParcelSizeDto> updateSize(@PathVariable String parcelId, @RequestBody UpdateParcelSizeDto updateParcelSize) {
        ParcelSize newSize = mapperFacade.map(updateParcelSize.getSize(), ParcelSize.class);

        recalculateSize.updateParcelSize(parcelId, newSize)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(updateParcelSize);
    }*/
}
