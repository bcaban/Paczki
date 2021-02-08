package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.*;
import com.webappsbusters.parcelmanagement.service.ParcelAccessService;
import com.webappsbusters.parcelmanagement.service.DetermineSize;
import com.webappsbusters.parcelmanagement.service.ParcelDeliveryTimeMock;
import com.webappsbusters.parcelmanagement.service.ParcelService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
public class ParcelController {

    private final ParcelService parcelService;
    private final ParcelAccessService parcelAccessService;
    private final MapperFacade mapperFacade;


    @Autowired
    public ParcelController(ParcelService parcelService, MapperFacade mapperFacade) {
        this.parcelService = parcelService;
        this.parcelAccessService = parcelAccessService;
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
    public ResponseEntity<UpdateParcelStatusDto> updateStatus(@PathVariable String parcelId,
                                                              @RequestBody UpdateParcelStatusDto updateParcelStatus) {
        ParcelStatus newStatus = mapperFacade.map(updateParcelStatus.getStatus(), ParcelStatus.class);

        parcelService.updateParcelStatus(parcelId, newStatus)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(updateParcelStatus);
    }

    @PutMapping("/parcels/{parcelId}/address")
    public ResponseEntity<ChangeDeliveryAddressDto> changeDeliveryAddress(
            @PathVariable String parcelId, @RequestBody ChangeDeliveryAddressDto changeDeliveryAddressDto) {

        parcelService.changeDeliveryAddress(parcelId, changeDeliveryAddressDto.getReceiverCity(),
                changeDeliveryAddressDto.getReceiverPostCode(), changeDeliveryAddressDto.getReceiverStreet())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        return ResponseEntity.ok(changeDeliveryAddressDto);
    }

    @PutMapping("/parcels/{parcelId}/name")
    public ResponseEntity<ParcelDto> changeParcelName(@PathVariable String parcelId, @RequestBody ChangeParcelNameDto changeParcelNameDto) {
        Parcel parcel = parcelService.changeName(parcelId, changeParcelNameDto.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(mapperFacade.map(parcel, ParcelDto.class));
    }

    @PostMapping(value = "/parcels")
    public ResponseEntity<ParcelDto> createParcel(@RequestBody ParcelDto parcelDto) {
        Parcel parcel = mapperFacade.map(parcelDto, Parcel.class);

        return ResponseEntity.ok(mapperFacade.map(parcelService.saveParcel(parcel), ParcelDto.class));
    }

    @PutMapping("/parcels/{parcelId}/timeToDeliver")
    public ResponseEntity<ParcelDto> changeParcelTimeToDeliver(@PathVariable String parcelId, @RequestBody UpdateParcelTimeToDeliverDto updateParcelTimeToDeliverDto) {
        Parcel parcel = parcelService.changeTimeToDelivery(parcelId, updateParcelTimeToDeliverDto.getTimeToDeliver())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(mapperFacade.map(parcel, ParcelDto.class));
    }
}
