package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.repository.UserRep;
import com.example.virtiverse.serviceImp.ServiceEmailEvent;
import com.example.virtiverse.serviceInterface.IEmailEventService;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Gestion Participation")
@RestController
@RequestMapping("/Participation")
@AllArgsConstructor
@CrossOrigin("*")
public class ParticipationController {
    IParticipationService participationService;
    IEmailEventService emailEventService;
    ServiceEmailEvent serviceEmail;
    UserRep userRep;

    @GetMapping("/ParticipationController")
    public List<Participation> retrieveAllParticipations() {
        return participationService.retrieveAllParticipations();
    }
    @PostMapping("/addParticipation/{idEvent}/{userName}")
    public Participation addParticipationWithIds(@RequestBody Participation participation, @PathVariable Long idEvent, @PathVariable String userName) {

            Participation savedParticipation = participationService.addParticipationWithIds(participation, idEvent, userName);
            // Envoyer un e-mail de confirmation à l'utilisateur
            String to = participation.getEmail();
            String subject = "Confirmation de participation";
            String text = "Votre participation à l'événement a été enregistrée avec succès.";
            emailEventService.sendConfirmationEmailWithQRCode(savedParticipation);
            return  savedParticipation;
    }
    @PutMapping("/updateParticipation")
    public Participation updateParticipations(@RequestBody Participation participation) {
        return participationService.updateParticipations(participation);
    }

    @GetMapping("/getById/{idParticipation}")
    public Participation retrieveParticipations(@PathVariable("idParticipation") Long idParticipation) {
        return participationService.retrieveParticipations(idParticipation);
    }

    @DeleteMapping("/DeleteById/{idParticipation}")
    public void removeParticipations(@PathVariable("idParticipation") Long idParticipation) {
        participationService.removeParticipations(idParticipation);
    }
    @GetMapping("/getParticipationsUser/{userName}")
    public List<Participation> retrieveAllParticipationsByUser(@PathVariable("userName")String userName) {
        return participationService.retrieveAllParticipationsByUser(userName);
    }
}

