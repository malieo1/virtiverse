package com.example.virtiverse.entities;

import com.example.virtiverse.entities.enums.TypeJeux;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Jeux implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idJeux;

    String nom;

    @Enumerated(EnumType.STRING)
    TypeJeux typeJeux;
    String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    User iduser;



}
