package com.example.virtiverse.repository;

import com.example.virtiverse.entities.PubItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PubItemRepository extends JpaRepository<PubItem,Long>{

    List<PubItem> findByDescriptionContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM PubItem p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.image) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.prix AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "CAST(p.numTelephone AS string) LIKE CONCAT('%', :keyword, '%')")
    List<PubItem> searchPubItems(@Param("keyword") String keyword);


    List<PubItem> findByOrderByEtatAsc();
    List<PubItem> findAllByOrderByEtatDesc();
}
