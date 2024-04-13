package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idEvent;
     String nomEvent;
     String organisateurEvent;
     String descriptionEvent;
     String lieuEvent;
    LocalDate dateDebutEvent;
    LocalDate dateFinEvent;
     float prixEvent;
     int capaciteEvent;
     String imageEvent;

    @ManyToOne
    @JsonIgnore
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    Set<Participation> participations;

}
