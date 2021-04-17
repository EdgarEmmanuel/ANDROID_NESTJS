package com.example.transfertandroidproject.http.retrofit.entities;

public class TokenRefresh {
    private String refreshToken;

    public TokenRefresh(String tokenRefresh){
        this.refreshToken = tokenRefresh;
    }

    public String getTokenRefresh() {
        return refreshToken;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.refreshToken = tokenRefresh;
    }
}
