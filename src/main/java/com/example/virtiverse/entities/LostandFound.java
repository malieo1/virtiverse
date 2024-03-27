package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LostandFound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pub;
    private String descripttion;
    private String image;
    private long num_tel;
}
