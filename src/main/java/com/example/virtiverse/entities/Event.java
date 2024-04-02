package com.example.virtiverse.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_event;
    private String nom_event;
    private String organisateur_event;
    private String description_event;
    private String lieu_event;
    private Date dateDebut_event;
    private Date dateFin_event;

    private float prix_event;

    private int capacite_event;
    private String image_event;




}
