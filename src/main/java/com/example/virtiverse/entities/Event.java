package com.example.virtiverse.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateDebutEvent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateFinEvent;
    float prixEvent;
    int capaciteEvent;
    String imageEvent;
    String statut;


    @ManyToOne
    @JsonIgnore
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    @Cascade(CascadeType.ALL)
    Set<Participation> participations;
}

