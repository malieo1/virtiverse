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
    @GetMapping("/retreiveAvis")
    public List<Avis> retreiveAvis() {
        return avisInterface.retreiveAvis();
    }
    @PostMapping("/AddAvis")
    public Avis AddAvis(@RequestBody Avis avis) {
        return avisInterface.AddAvis(avis);
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
}
