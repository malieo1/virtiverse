package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Participation;

import java.util.List;
import java.util.Map;

public interface IParticipationService {
    List<Participation> retrieveAllParticipations();
    Participation addParticipationWithIds(Participation participation, Long idEvent, Long id);
    Participation updateParticipations(Long idParticipation, Participation updatedParticipation);
    Participation retrieveParticipations(Long idParticipation);
    void removeParticipations (Long idParticipation);

     List<Participation> retrieveAllParticipationsByUser(Long id);
     byte[] generateQRCodeForParticipation(Participation participation);
    public Map<Long, Integer> getEventParticipationCounts();

}
