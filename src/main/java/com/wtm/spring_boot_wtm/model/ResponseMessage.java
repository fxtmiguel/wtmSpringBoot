package com.wtm.spring_boot_wtm.model;

public class ResponseMessage {
    private String message;

    // Constructor
    public ResponseMessage(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
}
