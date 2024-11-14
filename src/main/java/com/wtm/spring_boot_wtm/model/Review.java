package com.wtm.spring_boot_wtm.model;

import jakarta.persistence.*;

@Entity
@Table(name="reviews")

public class Review {

    /*
    * Need to connect to the front end
    * Need a bar ID for the review that the bar is for
    * Need a user ID for the posting of review
    * Can get rid of the char count because it is not needed anymore (done)
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewText;

    // Constructors, Getters, and Setters
    public Review() {}

    public Review(String reviewText) {
        this.reviewText = reviewText;
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

}

