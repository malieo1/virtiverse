package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.EventRep;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.repository.ParticipationRep;
import com.example.virtiverse.serviceInterface.IParticipationService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceParticipation implements IParticipationService {
    ParticipationRep participationRep;
    EventRep eventRep;
    OurUserRepo userRep;

    @Override
    public List<Participation> retrieveAllParticipations() {
        return participationRep.findAll();
    }

    @Override
    public Participation addParticipationWithIds(Participation participation, Long idEvent, Long id) {
        Event event = eventRep.findById(idEvent).orElse(null);
        // Récupérer l'utilisateur
        Optional<User> optionalUser = userRep.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        User user = optionalUser.get();

        if (event == null) {
            throw new IllegalArgumentException("Event with id " + idEvent + " not found");
        }

        // Vérifier la capacité restante de l'événement
        int capaciteRestante = event.getCapaciteEvent() - participation.getNbPlace();
        long numTel = participation.getNumtel();
        if (capaciteRestante >= 0) {
            if (!participationRep.isValidTunisianPhoneNumber(Long.toString(numTel))) {
                throw new IllegalArgumentException("Le numéro de téléphone doit être un numéro tunisien valide.");
            }
            // Valider l'adresse e-mail
            if (!participationRep.isValidEmail(participation.getEmail())) {
                throw new IllegalArgumentException("L'adresse e-mail doit être une adresse valide.");
            }
            // Mettre à jour la capacité restante de l'événement
            event.setCapaciteEvent(capaciteRestante);
            eventRep.save(event);
            participation.setEvent(event);
            participation.setUser(user);
            // Vérifier si la capacité est devenue 0
            return participationRep.save(participation);
        }
        else if (capaciteRestante < 0 && event.getCapaciteEvent()==0) {
            throw new IllegalArgumentException("We're sorry, this event is complete.");
        }
        else {
            throw new IllegalArgumentException("Only " + event.getCapaciteEvent() + " places are left for this event.");
        }
    }

    @Override
    public Participation updateParticipations(Long idParticipation, Participation updatedParticipation) {
        Participation existingParticipation = participationRep.findById(idParticipation)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + idParticipation));
        int oldNbPlace = existingParticipation.getNbPlace();
        int newNbPlace = updatedParticipation.getNbPlace();
        int difference = newNbPlace - oldNbPlace;

        existingParticipation.setEmail(updatedParticipation.getEmail());
        existingParticipation.setNbPlace(newNbPlace);
        existingParticipation.setNumtel(updatedParticipation.getNumtel());

        // Mettre à jour la capacité de l'événement
        Event event = existingParticipation.getEvent();
        int oldCapacity = event.getCapaciteEvent();
        int newCapacity = oldCapacity - difference; // Mettez à jour la capacité en fonction de la différence
        event.setCapaciteEvent(newCapacity);
        eventRep.save(event); // Enregistrer la mise à jour de l'événement

        return participationRep.save(existingParticipation);
    }

    @Override
    public Participation retrieveParticipations(Long idParticipation) {
        return participationRep.findById(idParticipation).orElse(null);
    }

    @Override
    public void removeParticipations(Long idParticipation) {

        Participation participation = participationRep.findById(idParticipation)
                .orElseThrow(() -> new RuntimeException("Participation not found with id: " + idParticipation));

        // Récupérer le nombre de places de la participation
        int nbPlace = participation.getNbPlace();

        // Supprimer la participation
        participationRep.deleteById(idParticipation);

        // Récupérer l'événement correspondant à la participation
        Event event = participation.getEvent();

        // Incrémenter la capacité de l'événement en fonction du nombre de places de la participation supprimée
        int oldCapacity = event.getCapaciteEvent();
        int newCapacity = oldCapacity + nbPlace; // Incrémenter la capacité en fonction du nombre de places supprimées
        event.setCapaciteEvent(newCapacity);
        eventRep.save(event); // Enregistrer la mise à jour de l'événement
    }

    @Override
    public List<Participation> retrieveAllParticipationsByUser(Long id) {
        return participationRep.findByUserId(id);
    }

    @Override
    public byte[] generateQRCodeForParticipation(Participation participation) {
        String id = participationRep.findIdByParticipationId(participation.getIdParticipation());
        String participationInfo = "Participation ID: " + participation.getIdParticipation() + "\n" +
                "User ID: " + id + "\n" +
                "Event: " + participation.getEvent().getNomEvent() + "\n" +
                "Email: " + participation.getEmail() + "\n" +
                "Phone: " + participation.getNumtel();

        try {
            // Générer le code QR
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(participationInfo, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            // Convertir le code QR en tableau d'octets et le renvoyer
            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace(); // Gérer les erreurs de génération du code QR
            return null;
        }
    }

    @Override
    public Map<Long, Integer> getEventParticipationCounts() {
        List<Object[]> counts = participationRep.countParticipationsByEvent();
        Map<Long, Integer> eventParticipationCounts = new HashMap<>();
        for (Object[] count : counts) {
            Long eventId = ((Number) count[0]).longValue(); // Convertir en Long
            Long participationCount = (Long) count[1]; // Pas besoin de conversion
            eventParticipationCounts.put(eventId, participationCount.intValue()); // Convertir en Integer
        }
        return eventParticipationCounts;
    }


}
