package com.example.festival.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String nickName;
    private String password;
}
