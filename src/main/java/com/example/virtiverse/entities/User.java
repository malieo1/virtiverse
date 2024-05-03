package com.example.virtiverse.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String role;



}
