package com.example.virtiverse.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Participation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idParticipation;
    long numtel;
    String email;
    int nbPlace;

    @JsonIgnore
    @ManyToOne
    Event event;

    @JsonIgnore
    @ManyToOne
    User user;
}
