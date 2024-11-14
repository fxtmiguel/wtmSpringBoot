package com.wtm.spring_boot_wtm.repository;

import com.wtm.spring_boot_wtm.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Long> {
}
