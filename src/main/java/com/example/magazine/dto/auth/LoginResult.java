package com.example.magazine.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private String email;
    private String token;

    public LoginResult(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
