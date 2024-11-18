package com.wtm.spring_boot_wtm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_text", nullable = false, length = 500)
    private String reviewText;

    // Use @Column to specify the exact column name in the database
    @Column(name = "bar_id", nullable = false)  // Maps to bar_id column in the database
    private Long barId;  // Renamed to follow Java naming conventions

    @Column(name = "user_id", nullable = false)  // Maps to user_id column in the database
    private Long userId;  // Renamed to follow Java naming conventions

    // Constructors, Getters, and Setters

    public Review() {}

    public Review(String reviewText, Long barId, Long userId) {
        this.reviewText = reviewText;
        this.barId = barId;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
