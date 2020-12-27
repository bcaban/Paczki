package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.ParcelHistoriesDto;
import com.webappsbusters.parcelmanagement.service.HistoryService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
public class HistoryController {

    private final HistoryService historyService;
    private final MapperFacade mapperFacade;

    public HistoryController(HistoryService historyService, MapperFacade mapperFacade) {
        this.historyService = historyService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping(value = "/parcels/{parcelId}/history")
    public ResponseEntity<ParcelHistoriesDto> getParcelHistory(@PathVariable String parcelId) {

        return ResponseEntity.ok(historyService.getParcelHistoryById(parcelId).
                map(parcelHistories -> mapperFacade.map(parcelHistories, ParcelHistoriesDto.class)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
