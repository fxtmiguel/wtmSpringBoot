package com.wtm.spring_boot_wtm.controller;

import com.wtm.spring_boot_wtm.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bars")
public class BarController {
    @Autowired
    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;
    }

    @PostMapping
    public ResponseEntity<?> addBar(@RequestBody BarRequest barRequest) {
        barService.addBarIfNotExists(barRequest.getPlaceId(), barRequest.getName());
        return ResponseEntity.ok("Bar processed successfully");
    }
}

// DTO for request payload
class BarRequest {
    private String placeId;
    private String name; // Optional: If sent from the frontend

    // Getters and Setters
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
