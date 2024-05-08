

package com.example.virtiverse.entities;

import com.example.virtiverse.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Raba3 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idRaba3;
    String description;

    int nombrePlaces;
    Date dateDebut;
    Date dateFin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRaba3")
    List<User> users;

    @ManyToOne
    @JsonIgnore
    User user;

    @ManyToOne
    @JsonIgnore
    Jeux jeux ;
}

