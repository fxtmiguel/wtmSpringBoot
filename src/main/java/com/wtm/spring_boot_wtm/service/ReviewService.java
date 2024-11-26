package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Review;
import com.wtm.spring_boot_wtm.repository.IReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    private IReviewRepository reviewRepository;

    ReviewService(IReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Save a review
    @Override
    @Transactional
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    // Get reviews by barId (bar_id in the database)
    @Override
    public List<Review> getReviewsByBarId(Long barId) {
        return reviewRepository.findByBarId(barId);
    }

    // Get reviews by userId (user_id in the database)
    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    // Get all reviews
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Delete review by ID
    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new NoSuchElementException("Review with id " + reviewId + " not found.");
        }
    }
}
