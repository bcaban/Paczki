package com.webappsbusters.parcelmanagement.controller;
import com.webappsbusters.parcelmanagement.domain.*;
import com.webappsbusters.parcelmanagement.service.DetermineSize;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        int length = parcelDimensionsDto.getDummylength();
        int width = parcelDimensionsDto.getDummywidth();
        int height = parcelDimensionsDto.getDummyheight();
        return ResponseEntity.ok(new ParcelSizeHolderDto(mapperFacade.map(determineSize.DetermineDummyParcelSize(length,width,height), ParcelSizeDto.class)));
    }
}
