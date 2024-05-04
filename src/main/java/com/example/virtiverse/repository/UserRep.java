package com.example.virtiverse.repository;

import com.example.virtiverse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Long> {
   // User findById(Long id);
}
