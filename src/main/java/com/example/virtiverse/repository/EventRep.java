package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRep extends JpaRepository<Event,Long> {
    @Query("SELECT e FROM Event e WHERE e.nom_event LIKE %:nom_event%")
    List<Event> findByNom_eventContainingIgnoreCase(@Param("nom_event") String nom_event);

    @Query("SELECT e FROM Event e ORDER BY e.prix_event DESC")
    List<Event> findAllOrderByPrixEventDesc();
    @Query("SELECT e FROM Event e WHERE e.dateDebut_event > :dateDebut")
    List<Event> findByDateDebut_eventEquals(@Param("dateDebut") LocalDate dateDebut);



}
