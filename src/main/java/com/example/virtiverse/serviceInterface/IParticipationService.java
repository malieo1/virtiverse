package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.entities.PubItem;

import java.util.List;

public interface IParticipationService {
    List<Participation> retrieveAllParticipations();
    Participation addParticipations(Participation participation);
    Participation updateParticipations(Participation participation);
    Participation retrieveParticipations(Long idParticipation);
    void removeParticipations (Long idParticipation);
    Participation addParticipationWithIds(Participation participation, Long idEvent, String userName);


}
