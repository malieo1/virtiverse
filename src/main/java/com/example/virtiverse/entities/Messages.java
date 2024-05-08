package com.example.virtiverse.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Messages  {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    long idmessage;
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate sendat;
    @ManyToOne(cascade = CascadeType.ALL)
    User iduser;
    @ManyToOne(cascade = CascadeType.ALL)
    LostandFound idpub ;

}
