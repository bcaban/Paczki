package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.*;
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
    private final MapperFacade mapperFacade;


    @Autowired
    public ParcelController(DetermineSize determineSize, ParcelService parcelService, MapperFacade mapperFacade) {
        this.determineSize = determineSize;
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

    //It will be helpful in the future
    @PostMapping(value = "/parcels")
    public ResponseEntity<ParcelDto> createParcel(@RequestBody ParcelDto parcelDto) {
        Parcel parcel = mapperFacade.map(parcelDto, Parcel.class);
        parcel.setStatus(ParcelStatus.ID_ADDED);
        parcel.setSize(determineSize.calculateParcelSize(parcel.getLength(), parcel.getWidth(), parcel.getHeight()));
        parcel.setTimeToDeliver(ParcelDeliveryTimeMock.mockTime(parcel.getStatus()));
        return ResponseEntity.ok(mapperFacade.map(parcelService.saveParcel(parcel), ParcelDto.class));
    }

    public void saveParcel() {
        Parcel parcel = Parcel.builder().status(ParcelStatus.ID_ADDED).height(15).length(12).receiverCity(
                "Lodz").receiverPostCode("92-089").receiverStreet("Mala").senderCity("Warsaw").senderPostCode("98-987"
        ).senderStreet("Wielka").size(ParcelSize.LARGE).timeToDeliver(Duration.ofDays(3L)).weightInKg(25).width(16).build();

        ParcelHistory parcelHistory = new ParcelHistory(1, ParcelStatus.ID_ADDED, LocalDateTime.now(), parcel);
        List<ParcelHistory> parcelHistories = new ArrayList<>();
        parcelHistories.add(parcelHistory);
        parcel.setParcelId(UUID.randomUUID().toString());
        parcel.setParcelHistories(parcelHistories);

        parcelService.saveParcel(parcel);
    }
}
