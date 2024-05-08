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

    @GetMapping("/retrieveUserGameSession/{name}")
    public List<Raba3> retrieveUserGameSession(@PathVariable("name") String userName) {
        return raba3Service.retrieveUserGameSession(userName);
    }

    @PostMapping("/addGameSession")
    public Raba3 addGameSession(@RequestBody Raba3 raba3) {
        return raba3Service.addGameSession(raba3);
    }

    @PutMapping("/updateGameSession/{idRaba3}")
    public Raba3 updateGameSession(@PathVariable("idRaba3") Long idRaba3,@RequestBody  Raba3 updatedraba3) {

        return raba3Service.updateGameSession(idRaba3,updatedraba3);
    }

    @DeleteMapping("/removeGameSession/{idRaba3}")
    public void removeGameSession(@PathVariable("idRaba3") Long idRaba3) {

        raba3Service.removeGameSession(idRaba3);
    }

    @PostMapping("/addGameSessionAndAssignToGame/{idJeux}")
    public Raba3 addGameSessionAndAssignToGame(@RequestBody Raba3 raba3,@PathVariable("idJeux") long idJeux) {
        return raba3Service.addGameSessionAndAssignToGame(raba3, idJeux);
    }

    @GetMapping("/retieveGameSessionSpecificUser/{idRaba3}/{name}")
    public Raba3 retieveGameSessionSpecificUser(@PathVariable("idRaba3") Long idRaba3,@PathVariable("name") String name) {
        return raba3Service.retieveGameSessionSpecificUser(idRaba3, name);
    }

    @PostMapping("/addGameSessionAndAssignToGameAndUser/{idJeux}/{id}")

    public Raba3 addGameSessionAndAssignToGameAndUser(@RequestBody Raba3 raba3, @PathVariable("idJeux") long idJeux,@PathVariable("id") long id) {
        return raba3Service.addGameSessionAndAssignToGameAndUser(raba3, idJeux, id);
    }

    @PostMapping("/addUserToSession/{idRaba3}/{id}")
    public void addUserToSession(@PathVariable("idRaba3") Long idRaba3, @PathVariable("id") Long id) {
        raba3Service.addUserToSession(idRaba3, id);
    }

    @DeleteMapping("/removeUserFromSession/{idRaba3}/{id}")
    public void removeUserFromSession(@PathVariable("idRaba3") Long idRaba3,@PathVariable("id") Long id) {
        raba3Service.removeUserFromSession(idRaba3, id);
    }
}
