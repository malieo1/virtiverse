package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.repository.MaisonRepository;
import com.example.virtiverse.serviceImp.MaisonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/maison")
@AllArgsConstructor

public class MaisonController {
    MaisonService maisonService;
    @GetMapping("/list")
    public List<Maison> getAllMaisons() {
        return maisonService.getAllMaisons();
    }

    @PostMapping("/addMaison")
    public Maison addMaison(@RequestBody Maison maison) {
        return maisonService.addMaison(maison);
    }

    @PutMapping("/updateMaison/{id_maison}")
    public Maison updateMaison(@RequestBody Maison maison , @PathVariable("id_maison") Long id_maison) {
        Maison m =maisonService.getMaison(id_maison);
        maison.setId_maison(m.getId_maison());
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

}
