
package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
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

    @ManyToMany(cascade = CascadeType.ALL)
    Set<User> session;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Jeux jeux ;
}
