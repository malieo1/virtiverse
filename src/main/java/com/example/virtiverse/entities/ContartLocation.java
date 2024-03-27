package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ContartLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contart;
    private Date date_debut;
    private Date date_fin;

}
