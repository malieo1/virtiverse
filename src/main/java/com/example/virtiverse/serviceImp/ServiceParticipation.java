package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.EventRep;
import com.example.virtiverse.repository.ParticipationRep;
import com.example.virtiverse.repository.UserRep;
import com.example.virtiverse.serviceInterface.IParticipationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceParticipation implements IParticipationService {
    ParticipationRep participationRep;
    EventRep eventRep;
    UserRep userRep;

    @Override
    public List<Participation> retrieveAllParticipations() {
        return participationRep.findAll();
    }

    @Override
    public Participation addParticipationWithIds(Participation participation, Long idEvent, String userName) {
        Event event = eventRep.findById(idEvent).orElse(null);
        User user = userRep.findByUserName(userName);

        if (event == null) {
            throw new IllegalArgumentException("Event with id " + idEvent + " not found");
        }

        if (user == null) {
            throw new IllegalArgumentException("User with username " + userName + " not found");
        }

        // Vérifier la capacité restante de l'événement
        int capaciteRestante = event.getCapaciteEvent() - participation.getNbPlace();

        if (capaciteRestante >= 0) {
            // Mettre à jour la capacité restante de l'événement
            event.setCapaciteEvent(capaciteRestante);
            eventRep.save(event);
            participation.setEvent(event);
            participation.setUser(user);
            // Vérifier si la capacité est devenue 0
            return participationRep.save(participation);
        }
        else if (capaciteRestante < 0 && event.getCapaciteEvent()==0) {
            throw new IllegalArgumentException("Désolé, l'évènement est complet.");
        }
        else {
            throw new IllegalArgumentException("Il ne reste que " + event.getCapaciteEvent() + " places pour cet événement.");

        }
    }

    @Override
    public Participation updateParticipations(Participation participation) {
        return participationRep.save(participation);
    }

    @Override
    public Participation retrieveParticipations(Long idParticipation) {
        return participationRep.findById(idParticipation).orElse(null);
    }

    @Override
    public void removeParticipations(Long idParticipation) {
        participationRep.deleteById(idParticipation);
    }

}
