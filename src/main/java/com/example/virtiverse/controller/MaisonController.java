package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceImp.MaisonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/maison")
@AllArgsConstructor

public class MaisonController {
    MaisonService maisonService;
    OurUserRepo userRepository;
    @GetMapping("/list")
    public List<Maison> getAllMaisons() {
        return maisonService.getAllMaisons();
    }


    @PostMapping("/addMaison")
    public Maison addMaison(@RequestBody Maison maison) {
        return maisonService.addMaison(maison);
    }

    @PostMapping("/addMaisonbyuser")
    public Maison addMaisonByUser(@Validated @RequestBody Maison maison , @RequestParam Long id) {
        return maisonService.addMaisonByUser(maison,id);
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
    @GetMapping("/utilisateurs/{id}/maisons")
    public List<Maison> getMaisonsByUtilisateur(@PathVariable Long id) {
        return maisonService.getMaisonsByUtilisateur(id);
    }
    /*@PostMapping("/{maisonId}/demandeur")
    public Maison ajouterDemandeur(@PathVariable Long maisonId, @RequestBody User demandeur) {
        return maisonService.ajouterDemandeur(maisonId, demandeur);
    }

     */
    @PostMapping("/{maisonId}/demandeur/{demandeurid}")
    public Maison ajouterDemandeur(@PathVariable Long maisonId, @PathVariable Long demandeurid) {
        return maisonService.ajouterDemandeur(maisonId, demandeurid);
    }
    @DeleteMapping("/{maisonId}/demandeurs/{idemandeur}")
    public void supprimerDemandeur(@PathVariable Long maisonId, @PathVariable Long idemandeur) {
        maisonService.supprimerDemandeur(maisonId, idemandeur);
    }

    @GetMapping("/list_page")
    public Page<Maison> findAllMaisonsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return maisonService.findAllMaisonsPage(pageable);
    }


}
