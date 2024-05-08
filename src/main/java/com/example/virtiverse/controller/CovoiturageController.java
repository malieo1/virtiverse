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
@PostMapping("/AddCovoiturage/{iduser}")

    public Covoiturage AddCovoiturage(@RequestBody Covoiturage covoiturage,@PathVariable Long iduser) {
        return covoiturageInterface.AddCovoiturage(covoiturage,iduser);
    }
@PutMapping("/updateCovoiturage/{id_cov}")
    public Covoiturage updateCovoiturage(@PathVariable Long id_cov ,@RequestBody Covoiturage covoiturage) {
        return covoiturageInterface.updateCovoiturage(id_cov,covoiturage);
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
        smsService.sendSms(to, "Reservation done ");
    }
    @PutMapping("/{covId}/nombre_placecov")
    public void updateNombrePlacecov(Long covId, int newNombrePlacecov) {
        covoiturageInterface.updateNombrePlacecov(covId, newNombrePlacecov);
    }
@GetMapping("/retrieveAllCovByUser/{id}")
    public List<Covoiturage> retrieveAllCovByUser(@PathVariable Long id) {
        return covoiturageInterface.retrieveAllCovByUser(id);
    }
@GetMapping("/searchCovByDest/{destination}")
    public List<Covoiturage> searchCovByDest(@PathVariable String destination) {
        return covoiturageInterface.searchCovByDest(destination);
    }
@PostMapping("/ReserveCov/{id_cov}/{idUser}")
    public void ReserveCov(@PathVariable Long id_cov, @PathVariable Long idUser) {
        covoiturageInterface.ReserveCov(id_cov, idUser);
    }
}
