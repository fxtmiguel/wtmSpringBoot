package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Review;
import com.wtm.spring_boot_wtm.repository.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}
