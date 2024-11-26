// 6

package com.wtm.spring_boot_wtm;

import com.wtm.spring_boot_wtm.model.Review;
import com.wtm.spring_boot_wtm.repository.IReviewRepository;
import com.wtm.spring_boot_wtm.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private IReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveReview() {
        Review review = new Review();
        review.setId(1L);

        reviewService.saveReview(review);

        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testGetReviewsByBarId() {
        Long barId = 1L;
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewRepository.findByBarId(barId)).thenReturn(reviews);

        List<Review> result = reviewService.getReviewsByBarId(barId);

        assertEquals(2, result.size());
        verify(reviewRepository, times(1)).findByBarId(barId);
    }

    @Test
    void testGetReviewsByUserId() {
        Long userId = 1L;
        List<Review> reviews = Arrays.asList(new Review(), new Review(), new Review());
        when(reviewRepository.findByUserId(userId)).thenReturn(reviews);

        List<Review> result = reviewService.getReviewsByUserId(userId);

        assertEquals(3, result.size());
        verify(reviewRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testGetAllReviews() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();

        assertEquals(2, result.size());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testDeleteReview_ExistingReview() {
        Long reviewId = 1L;
        Review review = new Review();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        reviewService.deleteReview(reviewId);

        verify(reviewRepository, times(1)).deleteById(reviewId);
    }

    @Test
    void testDeleteReview_NonExistingReview() {
        Long reviewId = 1L;
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reviewService.deleteReview(reviewId);
        });

        assertEquals("Review with id " + reviewId + " not found.", exception.getMessage());
        verify(reviewRepository, never()).deleteById(reviewId);
    }
}
