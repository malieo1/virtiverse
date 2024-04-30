package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.entities.PubItem;
import org.apache.tomcat.util.modeler.ParameterInfo;

import java.util.List;

public interface IParticipationService {
    List<Participation> retrieveAllParticipations();
    Participation addParticipationWithIds(Participation participation, Long idEvent, Long id);
    Participation updateParticipations(Long idParticipation, Participation updatedParticipation);
    Participation retrieveParticipations(Long idParticipation);
    void removeParticipations (Long idParticipation);

     List<Participation> retrieveAllParticipationsByUser(Long id);
     byte[] generateQRCodeForParticipation(Participation participation);

}
