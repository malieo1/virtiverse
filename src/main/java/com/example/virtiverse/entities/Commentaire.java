package com.example.virtiverse.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commentaire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commentaire;



    @Column(nullable = true)

    private String contenu;

    @ManyToOne
    PubItem pubItem;

    @ManyToOne
    User user;

}
