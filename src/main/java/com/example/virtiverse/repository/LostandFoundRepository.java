package com.example.virtiverse.repository;

import com.example.virtiverse.entities.LostandFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LostandFoundRepository extends JpaRepository<LostandFound , Long> {
    List<LostandFound> findByStatusOrderByDatePub(String status);
    List<LostandFound> findByNameOrderByDatePub(String name);

    List<LostandFound> findByIduserId(long id);

}
