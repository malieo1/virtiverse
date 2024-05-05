package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.repository.MaisonRepository;

import java.util.List;

public interface IMaison {
    List <Maison> getAllMaisons();
    Maison addMaison (Maison maison );
    Maison updateMaison (Maison maison);
    Maison getMaison(Long id_maison);
    void deleteMaison (Long id_maison);
    List<Maison> searchMaisonsByAdresse(String adresse);
    List<Maison> findAllOrderByPrixAsc();
    List<Maison> findAllOrderByPrixDesc();
    List<Maison> filtrerParPrix(float prixMin,float prixMax);

}
