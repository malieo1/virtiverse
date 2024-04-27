package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Jeux;
import com.example.virtiverse.entities.enums.TypeJeux;

import java.util.List;

public interface JeuxInterface {

    List<Jeux> retrieveAllGames();
    Jeux retrieveGame (Long idJeux);

    Jeux addGame(Jeux jeux);
    Jeux updateGame(Jeux jeux);
    void removeGame (Long idJeux);

    List<Jeux> retrieveGameByType(TypeJeux typeJeux);

}
