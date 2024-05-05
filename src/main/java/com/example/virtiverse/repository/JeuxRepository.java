package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Jeux;
import com.example.virtiverse.entities.enums.TypeJeux;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeuxRepository extends JpaRepository<Jeux,Long> {
    List<Jeux> findByTypeJeux(TypeJeux typeJeux);
}
