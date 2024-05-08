package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.entities.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis,Long> {
    @Query("SELECT a FROM Avis a WHERE a.covoiturage.id_cov = :id_cov")
    List<Avis> findAvisByCovoiturageId(Long id_cov);
    List<Avis> findByIduserId(Long id);
}
