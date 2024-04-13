package com.example.virtiverse.repository;

import com.example.virtiverse.entities.PubItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PubItemRepository extends JpaRepository<PubItem,Long>{

    List<PubItem> findByDescriptionContainingIgnoreCase(String keyword);
    List<PubItem> findByOrderByEtatAsc();
    List<PubItem> findAllByOrderByEtatDesc();
}
