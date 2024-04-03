package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.entities.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis,Long> {
}
