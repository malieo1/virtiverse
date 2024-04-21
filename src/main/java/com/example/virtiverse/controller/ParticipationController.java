package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.repository.UserRep;
import com.example.virtiverse.serviceInterface.IEventService;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Gestion Participation")
@RestController
@RequestMapping("/Participation")
@AllArgsConstructor
public class ParticipationController {
    IParticipationService participationService;
    UserRep userRep;

    @GetMapping("/ParticipationController")
    public List<Participation> retrieveAllParticipations() {
        return participationService.retrieveAllParticipations();
    }
    @PostMapping("/addParticipation/{idEvent}/{userName}")
    public ResponseEntity<String> addParticipationWithIds(@RequestBody Participation participation, @PathVariable Long idEvent, @PathVariable String userName) {
        try {
            Participation savedParticipation = participationService.addParticipationWithIds(participation, idEvent, userName);
            return ResponseEntity.ok("Participation ajoutée avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
}

