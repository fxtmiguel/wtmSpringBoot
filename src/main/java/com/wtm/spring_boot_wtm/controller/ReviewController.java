package com.wtm.spring_boot_wtm.controller;

import com.wtm.spring_boot_wtm.model.Review;
import com.wtm.spring_boot_wtm.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> submitReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return new ResponseEntity<>("Review submitted successfully", HttpStatus.OK);
    }
}
