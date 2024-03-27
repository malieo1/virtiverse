package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {

    @Id
    private String roleName;
    private String roleDescription;
}
