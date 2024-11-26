package com.wtm.spring_boot_wtm.controller;

import com.wtm.spring_boot_wtm.model.Review;
import com.wtm.spring_boot_wtm.service.IReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Submit a new review
    @PostMapping
    public ResponseEntity<String> submitReview(@RequestBody Review review) {
        try {
            reviewService.saveReview(review);
            return new ResponseEntity<>("Review submitted successfully", HttpStatus.CREATED); // Use CREATED (201) for successful creation
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to submit review: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        try {
            List<Review> reviews = reviewService.getAllReviews();
            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no reviews found
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK); // Return 200 OK with reviews
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if an error occurs
        }
    }

    // Get reviews by barId (bar_id in the database)
    @GetMapping("/bar/{barId}")
    public ResponseEntity<List<Review>> getReviewsByBarId(@PathVariable Long barId) {
        try {
            List<Review> reviews = reviewService.getReviewsByBarId(barId);
            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No reviews found for the bar
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK); // Return reviews for the bar
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if error occurs
        }
    }

    // Get reviews by userId (user_id in the database)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable String userId) {
        // Validate that the userId is a valid numeric string
        try {
            if (userId == null || !userId.matches("\\d+")) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                // return new ResponseEntity<>("Invalid user ID format", HttpStatus.BAD_REQUEST); // Return error if invalid userId
            }

            Long userIdLong = Long.parseLong(userId); // Convert the userId to a Long
            List<Review> reviews = reviewService.getReviewsByUserId(userIdLong);

            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No reviews found for the user
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK); // Return reviews for the user
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 if error occurs
        }
    }
}
