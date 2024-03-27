package com.example.virtiverse.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
public class User {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;

}
