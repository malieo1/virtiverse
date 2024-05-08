package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Avis;
import com.example.virtiverse.serviceInterface.AvisInterface;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Avis")
@AllArgsConstructor

public class AvisController {
    AvisInterface avisInterface;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retreiveAvis")
    public List<Avis> retreiveAvis() {
        return avisInterface.retreiveAvis();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/AddAvis/{id_cov}/{id}")
    public Avis AddAvis(@RequestBody Avis avis,@PathVariable("id_cov") Long id_cov,@PathVariable Long id) {
        return avisInterface.AddAvis(avis,id_cov,id);
    }
@PutMapping("/updateAvis")
    public Avis updateAvis(@RequestBody Avis avis) {
        return avisInterface.updateAvis(avis);
    }
@DeleteMapping("/removeAvis/{id_avis}")
    public void removeAvis(@PathVariable("id_avis") Long id_avis) {
        avisInterface.removeAvis(id_avis);
    }
@GetMapping("/retreiveAvis/{id_avis}")
    public Avis retreiveAvis(@PathVariable("id_avis")Long id_avis) {
        return avisInterface.retreiveAvis(id_avis);
    }
    @CrossOrigin(origins = "http://localhost:4200")
@GetMapping("/FindByCovoiturage/{id_cov}")
    public List<Avis> FindByCovoiturage(@PathVariable("id_cov") Long id_cov) {
        return avisInterface.FindByCovoiturage(id_cov);
    }
@GetMapping("/retrieveAllAvisByUser/{id}")
    public List<Avis> retrieveAllAvisByUser(@PathVariable Long id) {
        return avisInterface.retrieveAllAvisByUser(id);
    }
}
