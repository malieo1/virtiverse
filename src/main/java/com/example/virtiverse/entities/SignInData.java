package com.example.virtiverse.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInData {
    private int userId;
    private String emailId;
    private String token;
    private String refreshToken;
}
