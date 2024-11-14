package com.wtm.spring_boot_wtm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://192.168.3.189:8080")
public class TestController {

    // Allow CORS for local testing if the frontend is served from a different origin
    @CrossOrigin(origins = "http://192.168.3.189:8080") // Replace with your frontend's IP and port // Adjust this in production for security
    @PostMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong");
    }
}
