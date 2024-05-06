package com.example.virtiverse.dto;

import com.example.virtiverse.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private Long id ;

    private int phoneNumber;
    private String userName;
    private String Name;
    private String error;
    private String message;
    private String token;
    private String image ;
    private String refreshToken;
    private String expirationTime;
    private String email;
    private String role;
    private String password;
    private User ourUsers;
}
