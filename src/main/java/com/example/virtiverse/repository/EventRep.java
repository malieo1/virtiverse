package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRep extends JpaRepository<Event,Long> {

    List<Event> findByOrganisateurEventContainingIgnoreCase(String organisateurEvent);

    @Query("SELECT e FROM Event e ORDER BY e.prixEvent DESC")
    List<Event> findAllOrderByPrixEventDesc();
    List<Event> findByDateDebutEvent(LocalDate dateDebutEvent);
    List<Event> findByStatut(String statut);

    List<Event> findByUserId(Long id);




}
