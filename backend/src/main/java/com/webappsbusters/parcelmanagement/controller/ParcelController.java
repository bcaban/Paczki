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

    private final DetermineSize determineSize;
    private final ParcelService parcelService;
    private final ParcelAccessService parcelAccessService;
    private final MapperFacade mapperFacade;


    @Autowired
    public ParcelController(DetermineSize determineSize, ParcelService parcelService, ParcelAccessService parcelAccessService, MapperFacade mapperFacade) {
        this.determineSize = determineSize;
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

    @GetMapping(value = "parcels/{parcelId}/clientcode")
    public String getClientCode(@PathVariable Parcel parcel) {
        return parcelAccessService.getClientCode(parcel);
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
        parcel.setStatus(ParcelStatus.ID_ADDED);
        parcel.getParcelHistories().add(ParcelHistory.builder().parcelId(parcel).status(ParcelStatus.ID_ADDED).timestamp(LocalDateTime.now()).build());
        parcel.setParcelAccess(ParcelAccess.builder()
                .adminCode("c1b2208e-dc02-4c4b-b290-d2bd1bac5b07")
                .clientCode("922bb15d-e772-485f-82c8-7bfc18e85677")
                .build());
        parcel.setSize(determineSize.calculateParcelSize(parcel.getLength(), parcel.getWidth(), parcel.getHeight()));
        parcel.setTimeToDeliver(ParcelDeliveryTimeMock.mockTime(parcel.getStatus()));
        return ResponseEntity.ok(mapperFacade.map(parcelService.saveParcel(parcel), ParcelDto.class));
    }

    @PutMapping("/parcels/{parcelId}/timeToDeliver")
    public ResponseEntity<ParcelDto> changeParcelTimeToDeliver(@PathVariable String parcelId, @RequestBody UpdateParcelTimeToDeliverDto updateParcelTimeToDeliverDto) {
        Parcel parcel = parcelService.changeTimeToDelivery(parcelId, updateParcelTimeToDeliverDto.getTimeToDeliver())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(mapperFacade.map(parcel, ParcelDto.class));
    }

    public void saveParcel() {
        Parcel parcel = Parcel.builder()
                .status(ParcelStatus.ID_ADDED)
                .height(15)
                .length(12)
                .receiverCity("Lodz")
                .receiverPostCode("92-089")
                .receiverStreet("Mala")
                .senderCity("Warsaw")
                .senderPostCode("98-987")
                .senderStreet("Wielka")
                .size(ParcelSize.LARGE)
                .timeToDeliver(Duration.ofDays(3L))
                .weightInKg(25).width(16)
                .parcelAccess(ParcelAccess.builder()
                        .adminCode("c1b2208e-dc02-4c4b-b290-d2bd1bac5b07")
                        .clientCode("922bb15d-e772-485f-82c8-7bfc18e85677")
                        .build())
                .build();

        ParcelHistory parcelHistory = new ParcelHistory(1, ParcelStatus.ID_ADDED, LocalDateTime.now(), parcel);
        List<ParcelHistory> parcelHistories = new ArrayList<>();
        parcelHistories.add(parcelHistory);
        parcel.setParcelId(UUID.randomUUID().toString());
        parcel.setParcelHistories(parcelHistories);


        parcelService.saveParcel(parcel);
    }
}
