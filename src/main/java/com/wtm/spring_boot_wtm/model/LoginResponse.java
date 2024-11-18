package com.wtm.spring_boot_wtm.model;

public class LoginResponse {
    private Long user_id;
    private String username;
    private String token;

    // Constructor
    public LoginResponse(Long user_id, String username) {
        this.user_id = user_id;
        this.username = username;
        // this.token = token;
    }

    // Getters
    public Long getUserId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    // Setters (if needed)
    public void setUserId(Long user_id) {
        this.user_id = this.user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
