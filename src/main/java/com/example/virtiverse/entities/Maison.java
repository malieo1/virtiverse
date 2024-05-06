package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_maison;

    private String adresse;
    private String image;
    private float prix ;
    private String description;
}
