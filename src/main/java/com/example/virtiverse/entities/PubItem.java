package com.example.virtiverse.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class PubItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pub;

    @Column(nullable = false)
    private String description;

    private String image;

    @Column(nullable = false)
    private float prix;

    @Column(nullable = false)
    private long numTelephone;

    private String etat;

    @Column(name = "date_post", nullable = false)
    private Date datePost;

    @ManyToOne
    User user;
    @OneToMany (mappedBy = "pubItem")
    private Set<Commentaire> Commentaire;

    @OneToMany (mappedBy="pub")
    private Set<LikeItem> Likeitem;
}
