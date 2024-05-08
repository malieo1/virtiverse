package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.*;
import com.example.virtiverse.repository.JeuxRepository;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.repository.Raba3Repository;
import com.example.virtiverse.serviceInterface.Raba3Interface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class Raba3Service implements Raba3Interface {

    Raba3Repository raba3Repository;
    JeuxRepository jeuxRepository;
    OurUserRepo userRepo;
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
    public List<Raba3> retrieveUserGameSession(String name) {
        return raba3Repository.findByUserName(name);
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
    public Raba3 updateGameSession(Long idRaba3, Raba3 updatedraba3) {
        Raba3 existingRaba3 = raba3Repository.findById(idRaba3).orElseThrow(() -> new RuntimeException("Session not found with id: " + idRaba3));
        existingRaba3.setDescription(updatedraba3.getDescription());
        existingRaba3.setDateDebut(updatedraba3.getDateDebut());
        existingRaba3.setDateFin(updatedraba3.getDateFin());
        existingRaba3.setNombrePlaces(updatedraba3.getNombrePlaces());
        return raba3Repository.save(existingRaba3);
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

    @Override
    public Raba3 retieveGameSessionSpecificUser(Long idRaba3, String name) {
        return raba3Repository.findByIdRaba3AndUserName(idRaba3, name);
    }

    @Override
    public Raba3 addGameSessionAndAssignToGameAndUser(Raba3 raba3, long idJeux, long id) {
        Jeux jeux = jeuxRepository.findById(idJeux).orElse(null);
        User user = userRepo.findById(id).orElse(null);
        raba3.setJeux(jeux);
        raba3.setUser(user);
        return raba3Repository.save(raba3);
    }

    public void addUserToSession(Long idRaba3, Long id) {
        Raba3 session = raba3Repository.findById(idRaba3).orElseThrow(() -> new IllegalArgumentException("Session not found"));
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Add user to the session
        session.getUsers().add(user);
        // Update the session
        raba3Repository.save(session);
    }


    public void removeUserFromSession(Long idRaba3, Long id) {
        Optional<Raba3> raba3Optional = raba3Repository.findById(idRaba3);
        if (raba3Optional.isPresent()) {
            Raba3 raba3 = raba3Optional.get();
            raba3.getUsers().removeIf(item -> item.getId().equals(id));

            raba3Repository.save(raba3);
        } else {
            throw new IllegalArgumentException("Cart not found with id: " + idRaba3);
        }
    }

}
