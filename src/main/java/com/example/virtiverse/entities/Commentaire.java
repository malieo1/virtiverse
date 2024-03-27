package com.example.virtiverse.entities;

import jakarta.persistence.*;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commentaire;

    @Column(nullable = false)
    private String contenu;
}
