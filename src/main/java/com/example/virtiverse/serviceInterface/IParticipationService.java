package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;

import java.util.List;

public interface IParticipationService {
    List<Participation> retrieveAllParticipations();
    Participation addParticipations(Participation participation);
    Participation updateParticipations(Participation participation);
    Participation retrieveParticipations(Long id_participation);
    void removeParticipations (Long id_participation);
}
