package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_avis;
    String objet;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date_avis;
    int statut;

    @JsonIgnore
    @ManyToOne
    Covoiturage covoiturage;
    @JsonIgnore
    @ManyToOne
    User iduser;




}
