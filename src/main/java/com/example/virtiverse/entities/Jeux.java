package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jeux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_jeux;

    private String type_jeux;
     private String image;

}
