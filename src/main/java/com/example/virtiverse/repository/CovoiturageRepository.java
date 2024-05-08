package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CovoiturageRepository extends JpaRepository<Covoiturage,Long> {

List <Covoiturage> findByUseridId(Long id);
List <Covoiturage> findByDestinationContainingIgnoreCase(String destination);
}
