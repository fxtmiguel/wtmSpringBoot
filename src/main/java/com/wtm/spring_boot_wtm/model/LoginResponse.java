package com.wtm.spring_boot_wtm.model;

public class LoginResponse {
    private Long userId;
    private String username;
    private String token;

    // Constructor
    public LoginResponse(Long userId, String username) {
        this.userId = userId;
        this.username = username;
        // this.token = token;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    // Setters (if needed)
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
