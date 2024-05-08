package com.example.virtiverse.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_maison;
    String adresse;
    private int nbrplacedispo;
    float prix ;
    String description;
    @ElementCollection
    List <String> images;
    @OneToMany(mappedBy = "m")

    List<ContratLocation> contratsLocation;

    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @ManyToMany (cascade = CascadeType.ALL)
    List<User> demandeurs;


}
