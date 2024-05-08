package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Jeux;
import com.example.virtiverse.entities.Raba3;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface Raba3Interface {
    List<Raba3> retrieveAllGameSessions(Long idJeux);
    List<Raba3> retrieveAllGameSessions2();

    List<Raba3> retrieveUserGameSession (String userName);

    Raba3 retrieveGameSession (Long idRaba3);

    Raba3 addGameSession(Raba3 raba3);
    Raba3 updateGameSession(Long idRaba3, Raba3 updatedraba3);
    void removeGameSession (Long idRaba3);

    Raba3 addGameSessionAndAssignToGame(Raba3 raba3, long idJeux);
    Raba3 retieveGameSessionSpecificUser(Long idRaba3, String name);
    Raba3 addGameSessionAndAssignToGameAndUser(Raba3 raba3, long idJeux, long id);





}
