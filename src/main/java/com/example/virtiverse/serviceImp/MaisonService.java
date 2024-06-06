package com.example.virtiverse.serviceImp;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.virtiverse.entities.ContratLocation;
import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.MaisonRepository;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceInterface.IMaison;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class MaisonService implements IMaison {
    @Autowired
    MaisonRepository maisonRepository;
    @Autowired
    OurUserRepo userRepository;
    private static final String
            containerName = "wadhah";
    // Définir la chaîne de connexion
    private static final String connectionString = "DefaultEndpointsProtocol=https;AccountName=wadhahdaoud;AccountKey=axmwySmbufN/2Z6vfigEXhcvCih9gf2rp4YNSpJTRfpsMKyY4OLZGuMPanDfSV8U4deBziC2AaIN+AStYZYACw==;EndpointSuffix=core.windows.net";

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
        Optional<User> optionalUser = userRepository.findById(maison.getUser().getId()); // Assurez-vous d'avoir une méthode findByUserName dans votre UserRepository
        User user = optionalUser.get();
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


    public List<Maison> getMaisonsByUtilisateur(Long id) {
        return maisonRepository.findByUserId(id);
    }

    public Maison addMaisonByUser(Maison maison, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            maison.setUser(user);
            return maisonRepository.save(maison);
        } else {
            throw new IllegalArgumentException("Utilisateur non trouvé pour l'identifiant : " + id);
        }
    }
    /*public Maison ajouterDemandeur(Long maisonId, User demandeur) {
        Maison maison = maisonRepository.findById(maisonId).orElse(null);
        Optional<User> optionalUser= userRepository.findById(demandeur.getId());
        User user = optionalUser.get();
        maison.getDemandeurs().add(user);
        return maisonRepository.save(maison);
    }

     */
    public Maison ajouterDemandeur(Long maisonId, Long demandeurid) {
        Maison maison = maisonRepository.findById(maisonId).orElse(null);
        Optional<User> optionalUser = userRepository.findById(demandeurid);
        User user = optionalUser.get();
        maison.getDemandeurs().add(user);
        return maisonRepository.save(maison);
    }
    public void supprimerDemandeur(Long maisonId, Long iddemandeur) {
        Maison maison = maisonRepository.findById(maisonId).orElse(null);
        maison.getDemandeurs().removeIf(demandeur -> demandeur.getId().equals(iddemandeur));
        maisonRepository.save(maison);
    }
    public Page<Maison> findAllMaisonsPage(Pageable pageable) {
        return maisonRepository.findAll(pageable);
    }

}
