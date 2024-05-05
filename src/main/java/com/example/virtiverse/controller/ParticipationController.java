package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceImp.ServiceEmailEvent;
import com.example.virtiverse.serviceInterface.IEmailEventService;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Tag(name = "Gestion Participation")
@RestController
@RequestMapping("/Participation")
@AllArgsConstructor
@CrossOrigin("*")
public class ParticipationController {
    IParticipationService participationService;
    IEmailEventService emailEventService;
    ServiceEmailEvent serviceEmail;
    OurUserRepo userRep;

    @GetMapping("/ParticipationController")
    public List<Participation> retrieveAllParticipations() {
        return participationService.retrieveAllParticipations();
    }
    @PostMapping("/addParticipation/{idEvent}/{id}")
    public Participation addParticipationWithIds(@RequestBody Participation participation, @PathVariable Long idEvent, @PathVariable Long id) {

            Participation savedParticipation = participationService.addParticipationWithIds(participation, idEvent, id);
            // Envoyer un e-mail de confirmation à l'utilisateur
            String to = participation.getEmail();
            String subject = "Confirmation de participation";
            String text = "Votre participation à l'événement a été enregistrée avec succès.";
            emailEventService.sendConfirmationEmailWithQRCode(savedParticipation);
            return  savedParticipation;
    }
    @PutMapping("/updateParticipation/{idParticipation}")
    public Participation updateParticipations(@PathVariable ("idParticipation")Long idParticipation, @RequestBody Participation participation) {
        return participationService.updateParticipations(idParticipation, participation);
    }

    @GetMapping("/getById/{idParticipation}")
    public Participation retrieveParticipations(@PathVariable("idParticipation") Long idParticipation) {
        return participationService.retrieveParticipations(idParticipation);
    }

    @DeleteMapping("/DeleteById/{idParticipation}")
    public void removeParticipations(@PathVariable("idParticipation") Long idParticipation) {
        participationService.removeParticipations(idParticipation);
    }
    @GetMapping("/getParticipationsUser/{id}")
    public List<Participation> retrieveAllParticipationsByUser(@PathVariable("id")Long id) {
        return participationService.retrieveAllParticipationsByUser(id);
    }

    @GetMapping("/qrCode/participation/{idParticipation}")
    public ResponseEntity<byte[]> generateQRCodeForParticipation(@PathVariable long idParticipation) {
        Participation participation = participationService.retrieveParticipations(idParticipation);
        if (participation == null) {
            return ResponseEntity.notFound().build();
        }
        // Générer le code QR pour la participation
        byte[] qrCodeBytes = participationService.generateQRCodeForParticipation(participation);
        if (qrCodeBytes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // Retourner le code QR en tant que réponse avec le type de contenu approprié
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeBytes);
    }
    @GetMapping("/statistics")
    public Map<Long, Integer> getEventParticipationCounts() {
        Map<Long, Integer> eventParticipationCounts = participationService.getEventParticipationCounts();
        return eventParticipationCounts;
    }
}

