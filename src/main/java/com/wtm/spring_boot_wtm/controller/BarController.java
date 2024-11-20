package com.wtm.spring_boot_wtm.controller;

import com.wtm.spring_boot_wtm.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bars")
public class BarController {

    private final BarService barService;

    @Autowired
    public BarController(BarService barService) {
        this.barService = barService;
    }

    // DTO for request payload to add a bar
    static class BarRequest {
        private String placeId;
        private String name;

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

    // DTO for the busyness update request payload
    static class BusynessUpdateRequest {
        private int busyness;

        // Getters and Setters
        public int getBusyness() {
            return busyness;
        }

        public void setBusyness(int busyness) {
            this.busyness = busyness;
        }
    }

    // Endpoint to add a bar
    @PostMapping
    public ResponseEntity<?> addBar(@RequestBody BarRequest barRequest) {
        barService.addBarIfNotExists(barRequest.getPlaceId(), barRequest.getName());
        return ResponseEntity.ok("Bar processed successfully");
    }

    // New endpoint to update busyness
    @PutMapping("/{placeId}/busyness")
    public ResponseEntity<?> updateBusyness(@PathVariable String placeId, @RequestBody BusynessUpdateRequest request) {
        boolean success = barService.updateBusyness(placeId, String.valueOf(request.getBusyness()));
        if (success) {
            return ResponseEntity.ok("Busyness updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update busyness");
        }
    }
}
