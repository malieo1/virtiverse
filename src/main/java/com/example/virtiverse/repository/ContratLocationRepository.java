package com.example.virtiverse.repository;

import com.example.virtiverse.entities.ContratLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratLocationRepository extends JpaRepository <ContratLocation, Long> {
    List<ContratLocation> findByMUserId(Long id);
}
