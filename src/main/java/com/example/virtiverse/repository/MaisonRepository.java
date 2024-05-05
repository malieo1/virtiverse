package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Maison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MaisonRepository extends JpaRepository <Maison,Long> , PagingAndSortingRepository<Maison, Long> {
    List<Maison> findByAdresse(String adresse);
    @Query("SELECT m FROM Maison m ORDER BY m.prix ASC")
    List<Maison> findAllOrderByPrixAsc();
    @Query("SELECT m FROM Maison m ORDER BY m.prix DESC")
    List<Maison> findAllOrderByPrixDesc();
    List<Maison> findAllByPrixIsBetween(float p1,float p2);

    List<Maison> findByUserId(Long id);
}
