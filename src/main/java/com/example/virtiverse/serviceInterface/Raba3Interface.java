package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Jeux;
import com.example.virtiverse.entities.Raba3;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface Raba3Interface {
    List<Raba3> retrieveAllGameSessions();
    List<Raba3> retrieveAllGameSessions2();

    Raba3 retrieveGameSession (Long idRaba3);

    Raba3 addGameSession(Raba3 raba3);
    Raba3 updateGameSession(Raba3 raba3);
    void removeGameSession (Long idRaba3);

    Raba3 addGameSessionAndAssignToGame(Raba3 raba3, long idJeux);
}
