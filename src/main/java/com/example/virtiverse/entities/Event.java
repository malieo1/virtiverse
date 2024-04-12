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
     Long id_event;
     String nom_event;
     String organisateur_event;
     String description_event;
     String lieu_event;
     LocalDate dateDebut_event;
     LocalDate dateFin_event;
     float prix_event;
     int capacite_event;
     String image_event;

    @ManyToOne
    @JsonIgnore
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    Set<Participation> participations;

}
