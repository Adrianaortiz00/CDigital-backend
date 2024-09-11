package com.cdigital.cdigital_backend.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AuthResponse {

    private Integer userId;
    private String token;
    private String userRole; 

}