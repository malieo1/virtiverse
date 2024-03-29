package com.example.virtiverse.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated
    @Column(nullable = true)
    private Action action;
    @ManyToOne
    PubItem pub;

    @OneToOne
    User user;

}
