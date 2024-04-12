package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.repository.UserRep;
import com.example.virtiverse.serviceInterface.IEventService;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
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
    @PostMapping("/addParticipation")
    public Participation addParticipations(@RequestBody Participation participation) {
        return participationService.addParticipations(participation);
    }
    @PutMapping("/updateParticipation")
    public Participation updateParticipations(@RequestBody Participation participation) {
        return participationService.updateParticipations(participation);
    }
    @GetMapping("/getById/{id_participation}")
    public Participation retrieveParticipations(@PathVariable ("id_participation") Long id_participation) {
        return participationService.retrieveParticipations(id_participation);
    }
    @DeleteMapping("/DeleteById/{id_participation}")
    public void removeParticipations(@PathVariable ("id_participation") Long id_participation) {
        participationService.removeParticipations(id_participation);
    }
    @PostMapping("/addParticipation/{id_event}/{userName}")
    public ResponseEntity<String> addParticipationWithIds(@RequestBody Participation participation, @PathVariable Long id_event, @PathVariable String userName) {
        Participation savedParticipation = participationService.addParticipationWithIds(participation, id_event, userName);

        if (savedParticipation == null) {
            return ResponseEntity.badRequest().body("Error: Event or User not found");
        }
        return null;
    }
}
