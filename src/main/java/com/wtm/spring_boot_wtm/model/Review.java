package com.wtm.spring_boot_wtm.model;

public class Review {
    private Long id;
    private String reviewText;
    private int charCount;

    // Constructors, Getters, and Setters
    public Review() {}

    public Review(String reviewText, int charCount) {
        this.reviewText = reviewText;
        this.charCount = charCount;
    }

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

    public int getCharCount() {
        return charCount;
    }

    public void setCharCount(int charCount) {
        this.charCount = charCount;
    }
}
