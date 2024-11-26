package com.wtm.spring_boot_wtm.model;

public class LoginResponse {
    private Long userid;
    private String username;
    private String token;

    // Constructor
    public LoginResponse(Long userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    // Getters
    public Long getUserId() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    // Setters (if needed)
    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
