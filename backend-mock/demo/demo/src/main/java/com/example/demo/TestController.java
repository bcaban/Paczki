package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

    @GetMapping("parcelservice/v1/parcels/{id}")
    public String getPackage() {
        return "{\n" +
                "    \"parcelId\": \"dea2b636-dc39-4623-85e3-fcac29d07e0d\",\n" +
                "    \"status\": \"wyslana\",\n" +
                "    \"senderCity\": \"Warszawa\",\n" +
                "    \"senderPostCode\": \"03-550\",\n" +
                "    \"senderStreet\": \"Uniejowska\",\n" +
                "    \"receiverCity\": \"Lodz\",\n" +
                "    \"receiverPostCode\": \"93-550\",\n" +
                "    \"receiverStreet\": \"Wolcznaska\",\n" +
                "    \"weightInKg\": 20,\n" +
                "    \"height\": 10,\n" +
                "    \"length\": 12,\n" +
                "    \"width\": 14,\n" +
                "    \"size\": \"L\"\n" +
                "}";
    }
}
