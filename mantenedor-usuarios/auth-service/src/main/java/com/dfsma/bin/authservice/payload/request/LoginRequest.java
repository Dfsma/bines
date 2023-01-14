package com.dfsma.bin.authservice.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String dscEmail;

    @NotBlank
    private String password;

    public String getDscEmail() {
        return dscEmail;
    }

    public void setDscEmail(String dscEmail) {
        this.dscEmail = dscEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
