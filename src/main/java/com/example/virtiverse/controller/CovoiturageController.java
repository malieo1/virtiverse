package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Covoiturage;
import com.example.virtiverse.serviceImp.SmsService;
import com.example.virtiverse.serviceInterface.CovoiturageInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Covoiturage")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CovoiturageController {
    CovoiturageInterface covoiturageInterface;
    SmsService smsService;

    @GetMapping("/retreiveCovoiturages")
    public List<Covoiturage> retreiveCovoiturages() {
        return covoiturageInterface.retreiveCovoiturages();
    }
@PostMapping("/AddCovoiturage")

    public Covoiturage AddCovoiturage(@RequestBody Covoiturage covoiturage) {
        return covoiturageInterface.AddCovoiturage(covoiturage);
    }
@PutMapping("/updateCovoiturage")
    public Covoiturage updateCovoiturage(@RequestBody Covoiturage covoiturage) {
        return covoiturageInterface.updateCovoiturage(covoiturage);
    }
@DeleteMapping("/removeCovoiturage/{idcov}")
    public void removeCovoiturage(@PathVariable("idcov") Long id_cov) {
        covoiturageInterface.removeCovoiturage(id_cov);

    }

@GetMapping("/retreiveCov/{idcov}")
    public Covoiturage retreiveCov(@PathVariable("idcov") Long id_cov) {
        return covoiturageInterface.retreiveCov(id_cov);
    }

    @GetMapping("/SendSms")
    public void sendSms(String to, String message) {
        smsService.sendSms(to, "Virtiverse");
    }
    @PutMapping("/{covId}/nombre_placecov")
    public void updateNombrePlacecov(Long covId, int newNombrePlacecov) {
        covoiturageInterface.updateNombrePlacecov(covId, newNombrePlacecov);
    }
}
