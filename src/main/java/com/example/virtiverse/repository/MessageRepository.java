package com.example.virtiverse.repository;

import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Messages, Long> {
}
