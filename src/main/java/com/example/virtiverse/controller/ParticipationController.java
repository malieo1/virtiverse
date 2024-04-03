package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.serviceInterface.IEventService;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Gestion Participation")
@RestController
@RequestMapping("/Participation")
@AllArgsConstructor
public class ParticipationController {
    IParticipationService participationService;

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
}
