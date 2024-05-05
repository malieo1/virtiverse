
package com.example.virtiverse.entities;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;

    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;



}

