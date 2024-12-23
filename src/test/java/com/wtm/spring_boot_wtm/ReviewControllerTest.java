// 8

package com.wtm.spring_boot_wtm;

import com.wtm.spring_boot_wtm.model.Review;
import com.wtm.spring_boot_wtm.service.IReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IReviewService reviewService;

    private Review review;

    @BeforeEach
    public void setUp() {
        // Prepare test data
        review = new Review("Amazing bar with great ambiance!", 1L, 1L);
    }

    @Test
    void testSubmitReview_Success() throws Exception {
        // Submit a new review
        mockMvc.perform(post("/api/reviews")
                        .contentType("application/json")
                        .content("{\"reviewText\":\"Amazing bar with great ambiance!\", \"barId\":1, \"userId\":1}"))
                .andExpect(status().isCreated()) // Expect 201 status (Created)
                .andExpect(content().string("Review submitted successfully"));
    }

    @Test
    void testGetAllReviews_Success() throws Exception {
        // Save the review first
        reviewService.saveReview(review);

        // Get all reviews
        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk()); // Expect 200 status (OK)
                // andExpect(jsonPath("$.length()").value(1)); // Expect 1 review in response
    }

    @Test
    void testGetAllReviews_NoContent() throws Exception {
        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk()); // Expect 200 status (No Content)
    }

    @Test
    void testGetReviewsByBarId_NoContent() throws Exception {
        mockMvc.perform(get("/api/reviews/bar/999"))
                .andExpect(status().isNoContent()); // Expect 204 status (No Content)
    }

    @Test
    void testGetReviewsByUserId_NoContent() throws Exception {
        mockMvc.perform(get("/api/reviews/user/999"))
                .andExpect(status().isNoContent()); // Expect 204 status (No Content)
    }

    @Test
    void testGetReviewsByUserId_InvalidUserId() throws Exception {
        mockMvc.perform(get("/api/reviews/user/abc"))
                .andExpect(status().isNoContent()); // Expect 204 status (No Content)
    }
}
