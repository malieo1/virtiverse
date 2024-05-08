package com.example.virtiverse.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString



public class LostandFound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long idPub;
     String name;
     String location;
     String description;
     String status;
     String image;
     String datePub;
     long num_tel;
     @ManyToOne

     User iduser;


}
