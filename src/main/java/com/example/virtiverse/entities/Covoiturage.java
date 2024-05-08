package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
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

public class Covoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


     Long id_cov;
     int nombre_placecov;
    @JsonFormat(pattern = "yyyy-MM-dd")

     LocalDate date_depart;
     String lieu_depart;
     String destination;
     String description;

     @ManyToOne
     User userid;

    @ManyToMany
    List<User> reservations;

}
