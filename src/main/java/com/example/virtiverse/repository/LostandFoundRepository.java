package com.example.virtiverse.repository;

import com.example.virtiverse.entities.LostandFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LostandFoundRepository extends JpaRepository<LostandFound , Long> {
}
