package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_participation;
     private long numtel;
     private String email;
     private int nb_place;
}
