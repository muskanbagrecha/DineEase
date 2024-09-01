package com.mb.DineEase.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private String userId;
    private String message;
    private String userRole;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthResponse(String token, String message, String userRole) {
        this.token = token;
        this.message = message;
        this.userRole = userRole;
    }

    public AuthResponse(String token, String userId, String message, String userRole) {
        this.token = token;
        this.userId = userId;
        this.message = message;
        this.userRole = userRole;
    }
}
