package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContratLocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_contrat;
    LocalDate date_debut;
    LocalDate date_fin;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    Maison m;
    @OneToMany (cascade = CascadeType.ALL)
    List<User> colocataires;
}
