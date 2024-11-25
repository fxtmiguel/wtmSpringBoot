package com.wtm.spring_boot_wtm.model;
import lombok.Data;

@Data
public class FavoriteRequest {
    private String userId;
    private String placeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
    
