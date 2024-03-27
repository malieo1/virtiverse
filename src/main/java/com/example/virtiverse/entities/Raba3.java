package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Raba3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_raba3;

    private int nombre_place;
    private Date dateDebut;

    private Date dateFin;


}
