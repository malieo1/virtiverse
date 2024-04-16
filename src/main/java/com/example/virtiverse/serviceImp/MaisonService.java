package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.repository.MaisonRepository;
import com.example.virtiverse.serviceInterface.IMaison;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MaisonService implements IMaison {
    MaisonRepository maisonRepository;
    @Override
    public List<Maison> getAllMaisons() {
        return maisonRepository.findAll();
    }

    @Override
    public Maison addMaison(Maison maison) {
        return maisonRepository.save(maison);
    }

    @Override
    public Maison updateMaison(Maison maison) {
        return maisonRepository.save(maison);
    }

    @Override
    public Maison getMaison(Long id_maison) {
        return maisonRepository.findById(id_maison).orElse(null);
    }

    @Override
    public void deleteMaison(Long id_maison) {
        maisonRepository.deleteById(id_maison);
    }
    @Override
    public List<Maison> searchMaisonsByAdresse(String adresse) {
        return maisonRepository.findByAdresse(adresse);
    }

    @Override
    public List<Maison> findAllOrderByPrixDesc() {
        return maisonRepository.findAllOrderByPrixDesc();
    }

    @Override
    public List<Maison> filtrerParPrix(float prixMin, float prixMax) {
        return maisonRepository.findAllByPrixIsBetween(prixMin,prixMax);
    }

    @Override
    public List<Maison> findAllOrderByPrixAsc() {
        return maisonRepository.findAllOrderByPrixAsc();
    }


}
