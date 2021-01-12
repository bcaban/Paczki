package com.webappsbusters.parcelmanagement.controller;

import com.webappsbusters.parcelmanagement.domain.*;
import com.webappsbusters.parcelmanagement.service.ParcelAccessService;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class ParcelAccessController {
    private final ParcelAccessService parcelAccessService;
    private final MapperFacade mapperFacade;

    @PostMapping(value = "/parcels/{parcelId}/access")
    public ResponseEntity<ParcelAccessStatusHolderDTO> checkAccess(@PathVariable String parcelId, @RequestBody ParcelAccessCodesDTO parcelAccessCodesDTO) {
        final ParcelAccessCodes parcelAccessCodes = mapperFacade.map(parcelAccessCodesDTO, ParcelAccessCodes.class);

        final ParcelAccessStatus parcelAccessStatusResult = parcelAccessService.checkAccess(parcelId, parcelAccessCodes).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ParcelAccessStatusDTO parcelAccessStatusDTO = mapperFacade.map(parcelAccessStatusResult, ParcelAccessStatusDTO.class);

        return ResponseEntity.ok(new ParcelAccessStatusHolderDTO(parcelAccessStatusDTO));
    }
}
