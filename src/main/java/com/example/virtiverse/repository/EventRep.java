package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRep extends JpaRepository<Event,Long> {

    List<Event> findByNomEventContainingIgnoreCase(String nomEvent);

    @Query("SELECT e FROM Event e ORDER BY e.prixEvent DESC")
    List<Event> findAllOrderByPrixEventDesc();
    List<Event> findByDateDebutEvent(LocalDate dateDebutEvent);




}
