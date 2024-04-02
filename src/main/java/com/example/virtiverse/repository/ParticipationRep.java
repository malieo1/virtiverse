package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRep extends JpaRepository<Participation,Long> {
}
