package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Jeux;
import com.example.virtiverse.entities.enums.TypeJeux;
import com.example.virtiverse.repository.JeuxRepository;
import com.example.virtiverse.serviceInterface.JeuxInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class JeuxService implements JeuxInterface {

    JeuxRepository jeuxRepository;
    @Override
    public List<Jeux> retrieveAllGames() {
        return jeuxRepository.findAll();
    }

    @Override
    public Jeux retrieveGame(Long idJeux) {
        return jeuxRepository.findById(idJeux).orElse(null);
    }

    @Override
    public Jeux addGame(Jeux jeux) {
        return jeuxRepository.save(jeux);
    }

    @Override
    public Jeux updateGame(Jeux jeux) {
        return jeuxRepository.save(jeux);
    }

    @Override
    public void removeGame(Long idJeux) {
        jeuxRepository.deleteById(idJeux);

    }

    @Override
    public List<Jeux> retrieveGameByType(TypeJeux typeJeux) {
        return jeuxRepository.findByTypeJeux(typeJeux);
    }
}
