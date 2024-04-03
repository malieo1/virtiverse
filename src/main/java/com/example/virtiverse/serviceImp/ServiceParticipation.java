package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.repository.EventRep;
import com.example.virtiverse.repository.ParticipationRep;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceParticipation implements IParticipationService {
    ParticipationRep participationRep;
    @Override
    public List<Participation> retrieveAllParticipations() {
        return participationRep.findAll();
    }

    @Override
    public Participation addParticipations(Participation participation) {
        return participationRep.save(participation);
    }

    @Override
    public Participation updateParticipations(Participation participation) {
        return participationRep.save(participation);
    }

    @Override
    public Participation retrieveParticipations(Long id_participation) {
        return participationRep.findById(id_participation).orElse(null);
    }

    @Override
    public void removeParticipations(Long id_participation) {
        participationRep.deleteById(id_participation);
    }
}
