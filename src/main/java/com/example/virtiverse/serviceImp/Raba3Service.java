package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Jeux;
import com.example.virtiverse.entities.Raba3;
import com.example.virtiverse.repository.JeuxRepository;
import com.example.virtiverse.repository.Raba3Repository;
import com.example.virtiverse.serviceInterface.Raba3Interface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class Raba3Service implements Raba3Interface {

    Raba3Repository raba3Repository;
    JeuxRepository jeuxRepository;
    @Override
    public List<Raba3> retrieveAllGameSessions(Long idJeux) {
        Jeux jeux = jeuxRepository.findById(idJeux).orElse(null);
        return raba3Repository.findByJeux(jeux);
    }

    @Override
    public List<Raba3> retrieveAllGameSessions2() {
         Sort sortByDate = Sort.by("dateDebut").descending();
         return raba3Repository.findAll(sortByDate);
    }

    @Override
    public List<Raba3> retrieveUserGameSession(String userName) {
        return raba3Repository.findByUserUserName(userName);
    }

    @Override
    public Raba3 retrieveGameSession(Long idRaba3) {
        return raba3Repository.findById(idRaba3).orElse(null);
    }

    @Override
    public Raba3 addGameSession(Raba3 raba3) {
        return raba3Repository.save(raba3);
    }

    @Override
    public Raba3 updateGameSession(Raba3 raba3) {
        return raba3Repository.save(raba3);
    }

    @Override
    public void removeGameSession(Long idRaba3) {
        raba3Repository.deleteById(idRaba3);

    }

    @Override
    public Raba3 addGameSessionAndAssignToGame(Raba3 raba3, long idJeux) {
        Jeux jeux = jeuxRepository.findById(idJeux).orElse(null);
        raba3.setJeux(jeux);
        return raba3Repository.save(raba3);
    }
}
