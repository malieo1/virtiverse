package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.ContratLocation;
import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.MaisonRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceInterface.IMaison;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class MaisonService implements IMaison {
    MaisonRepository maisonRepository;
    UserRepository userRepository;
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
        //return maisonRepository.findById(id_maison).orElse(null);
        Maison maison = maisonRepository.findById(id_maison).orElse(null);

        // Récupérer les détails de l'utilisateur associé à la maison
        User user = userRepository.findByUserName(maison.getUser().getUserName()); // Assurez-vous d'avoir une méthode findByUserName dans votre UserRepository

        // Attribuer l'utilisateur à la maison
        maison.setUser(user);

        return maison;
    }
    @Transactional
    public void deleteRelationBetweenMaisonAndUser(Long idMaison) {
        Maison maison = maisonRepository.findById(idMaison).orElse(null);
        if (maison != null) {
            maison.setUser(null); // Supprime la relation en mettant la référence à null
            maisonRepository.save(maison);
        }
    }
    @Override
    @Transactional
    public void deleteMaison(Long id_maison) {
        deleteRelationBetweenMaisonAndUser(id_maison);
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


    public List<Maison> getMaisonsByUtilisateur(String userName) {
        return maisonRepository.findByUserUserName(userName);
    }

    public Maison addMaisonByUser(Maison maison, String nom) {
        User user= userRepository.findByUserName(nom);
        maison.setUser(user);
        return maisonRepository.save(maison);
    }
    public Maison ajouterDemandeur(Long maisonId, User demandeur) {
        Maison maison = maisonRepository.findById(maisonId).orElse(null);
        User user= userRepository.findByUserName(demandeur.getUserName());
        maison.getDemandeurs().add(user);
        return maisonRepository.save(maison);
    }
    public void supprimerDemandeur(Long maisonId, String nomDemandeur) {
        Maison maison = maisonRepository.findById(maisonId).orElse(null);
        maison.getDemandeurs().removeIf(demandeur -> demandeur.getUserName().equals(nomDemandeur));
        maisonRepository.save(maison);
    }
}
