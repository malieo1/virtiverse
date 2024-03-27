package com.example.virtiverse.entities;

import jakarta.persistence.*;

@Entity
public class LikeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;
    @ManyToOne
    PubItem pub;

    @OneToOne
    User user;

}
