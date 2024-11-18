package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Review;
import java.util.List;

public interface IReviewService {
    void saveReview(Review review);

    List<Review> getReviewsByBarId(Long barId);  // Changed to camelCase method name

    List<Review> getReviewsByUserId(Long userId);  // Changed to camelCase method name

    List<Review> getAllReviews();

    void deleteReview(Long id);
}
