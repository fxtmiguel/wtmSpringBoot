package com.wtm.spring_boot_wtm.repository;

import com.wtm.spring_boot_wtm.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Long> {

    // Updated method names to use camelCase
    List<Review> findByBarId(Long barId);  // Changed to camelCase method name

    List<Review> findByUserId(Long userId);  // Changed to camelCase method name
}
