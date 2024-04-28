package com.example.virtiverse.controller;

import com.example.virtiverse.entities.ContratLocation;
import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.MaisonRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceImp.MaisonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/maison")
@AllArgsConstructor

public class MaisonController {
    MaisonService maisonService;
    UserRepository userRepository;
    @GetMapping("/list")
    public List<Maison> getAllMaisons() {
        return maisonService.getAllMaisons();
    }


    @PostMapping("/addMaison")
    public Maison addMaison(@RequestBody Maison maison) {
        return maisonService.addMaison(maison);
    }
   /* @PostMapping("/addMaisonbyuser")
    public ResponseEntity<String> addMaisonByUser(@RequestBody Maison maisonRequest) {
        // Convertir MaisonRequest en Maison entity
        Maison maison = new Maison();
        maison.setAdresse(maisonRequest.getAdresse());
        maison.setPrix(maisonRequest.getPrix());
        maison.setDescription(maisonRequest.getDescription());
        maison.setImages(maisonRequest.getImages());

        // Charger l'utilisateur à partir de la base de données en utilisant le nom d'utilisateur
        User user = userRepository.findByUserName(maisonRequest.getUser().getUserName());


        // Associer l'utilisateur à la maison
        if (user == null) {
            // Attribuer null à la propriété user de la maison
            maison.setUser(null);
        } else {
            // Associer l'utilisateur à la maison
            maison.setUser(user);
        }

        // Enregistrer la maison dans la base de données
        maisonService.addMaison(maison);

        return ResponseEntity.ok("Maison ajoutée avec succès");
    }
    */

    @PostMapping("/addMaisonbyser")
    public Maison addMaisonByUser(@Validated @RequestBody Maison maison , @RequestParam String nom) {
        return maisonService.addMaisonByUser(maison,nom);
    }




    @PutMapping("/updateMaison/{id_maison}")
    public Maison updateMaison(@RequestBody Maison maison , @PathVariable("id_maison") Long id_maison) {
        Maison m =maisonService.getMaison(id_maison);
        maison.setId_maison(m.getId_maison());
        maison.setUser(m.getUser());
        return maisonService.updateMaison(maison);
    }

    @GetMapping("/getMaison/{id_maison}")
    public Maison getMaison(@PathVariable("id_maison") Long id_maison) {
        return maisonService.getMaison(id_maison);
    }

    @DeleteMapping("/deleteMaison/{id_maison}")
    public void deleteMaison( @PathVariable ("id_maison")Long id_maison) {
        maisonService.deleteMaison(id_maison);
    }
    @GetMapping("/search/{adresse}")
    public List<Maison> searchMaisonsByAdresse(@PathVariable String adresse) {
        return maisonService.searchMaisonsByAdresse(adresse);
    }
    @GetMapping("/orderByPrixAsc")
    public List<Maison> findAllOrderByPrixAsc() {
        return maisonService.findAllOrderByPrixAsc();
    }
    @GetMapping("/orderByPrixDesc")
    public List<Maison> findAllOrderByPrixDesc() {
        return maisonService.findAllOrderByPrixDesc();
    }
    @GetMapping("/filtrerParPrix")
    public List<Maison> filtrerParPrix(@RequestParam float prixMin,@RequestParam float prixMax) {
        return maisonService.filtrerParPrix(prixMin,prixMax);
    }
    @GetMapping("/utilisateurs/{userName}/maisons")
    public List<Maison> getMaisonsByUtilisateur(@PathVariable String userName) {
        return maisonService.getMaisonsByUtilisateur(userName);
    }

}
