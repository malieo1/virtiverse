package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PubItem implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pub;

    private String name;
    private String description;
    private String image;
    private float prix;
    private long numTelephone;

    @Enumerated
    private Etat etat;

    @Column(name = "date_post", nullable = true)
    private Date datePost;
    @ManyToOne
    User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pubItem")
    @JsonIgnore
    private Set<Commentaire> Commentaire;
}
