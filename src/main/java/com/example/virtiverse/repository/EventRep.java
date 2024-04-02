package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRep extends JpaRepository<Event,Long> {
}
