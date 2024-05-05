package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Raba3;
import com.example.virtiverse.serviceImp.Raba3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/SessionJeux")
@CrossOrigin(origins = "http://localhost:4200")

public class Raba3Controller {
    Raba3Service raba3Service;

    @GetMapping("/retrieveAllGameSessions/{idJeux}")
    public List<Raba3> retrieveAllGameSessions(@PathVariable("idJeux") Long idJeux) {

        return raba3Service.retrieveAllGameSessions(idJeux);
    }

    @GetMapping("/retrieveAllGameSessions2")
    public List<Raba3> retrieveAllGameSessions2() {

        return raba3Service.retrieveAllGameSessions2();
    }

    @GetMapping("/retrieveGameSession/{idRaba3}")
    public Raba3 retrieveGameSession(@PathVariable("idRaba3") Long idRaba3) {
        return raba3Service.retrieveGameSession(idRaba3);
    }

    @GetMapping("/retrieveUserGameSession/{userName}")
    public List<Raba3> retrieveUserGameSession(@PathVariable("userName") String userName) {
        return raba3Service.retrieveUserGameSession(userName);
    }

    @PostMapping("/addGameSession")
    public Raba3 addGameSession(@RequestBody Raba3 raba3) {
        return raba3Service.addGameSession(raba3);
    }

    @PutMapping("/updateGameSession")
    public Raba3 updateGameSession(@RequestBody Raba3 raba3) {

        return raba3Service.updateGameSession(raba3);
    }

    @DeleteMapping("/removeGameSession/{idRaba3}")
    public void removeGameSession(@PathVariable("idRaba3") Long idRaba3) {

        raba3Service.removeGameSession(idRaba3);
    }

    @PostMapping("/addGameSessionAndAssignToGame")
    public Raba3 addGameSessionAndAssignToGame(Raba3 raba3, long idJeux) {
        return raba3Service.addGameSessionAndAssignToGame(raba3, idJeux);
    }
}