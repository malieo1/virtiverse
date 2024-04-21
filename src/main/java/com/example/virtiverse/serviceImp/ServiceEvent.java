package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.repository.EventRep;
import com.example.virtiverse.serviceInterface.IEventService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceEvent implements IEventService {
    EventRep eventRep;
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRep.findAll();
    }
    @Override
    public Event addEvent(Event event) {

        List<String> errors = new ArrayList<>();

        // Vérification des champs obligatoires
        if (StringUtils.isEmpty(event.getNomEvent())) {
            errors.add("Le champ 'Nom de l'événement' est obligatoire.");
        }
        if (StringUtils.isEmpty(event.getDescriptionEvent())) {
            errors.add("Le champ 'Description de l'événement' est obligatoire.");
        }
        if (StringUtils.isEmpty(event.getOrganisateurEvent())) {
            errors.add("Le champ 'Organisateur de l'événement' est obligatoire.");
        }
        if (StringUtils.isEmpty(event.getLieuEvent())) {
            errors.add("Le champ 'Lieu de l'événement' est obligatoire.");
        }
        if (StringUtils.isEmpty(event.getImageEvent())) {
            errors.add("Le champ 'Image de l'événement' est obligatoire.");
        }
        if (event.getDateDebutEvent() == null) {
            errors.add("Le champ 'Date de début de l'événement' est obligatoire.");
        }
        if (event.getDateFinEvent() == null) {
            errors.add("Le champ 'Date de fin de l'événement' est obligatoire.");
        }

        // Vérification du prix et de la capacité
        if (event.getPrixEvent() < 0) {
            errors.add("Le prix ne peut pas être négatif.");
        }
        if (event.getCapaciteEvent() < 0) {
            errors.add("La capacité ne peut pas être négative.");
        }

        // Vérification de la date de fin après la date de début
        if (event.getDateDebutEvent() != null && event.getDateFinEvent() != null &&
                event.getDateFinEvent().isBefore(event.getDateDebutEvent())) {
            errors.add("La date de fin ne peut pas être antérieure à la date de début.");
        }

        // Vérification de la date de début après la date actuelle
        if (event.getDateDebutEvent() != null && event.getDateDebutEvent().isBefore(LocalDate.now())) {
            errors.add("La date de début ne peut pas être antérieure à la date actuelle.");
        }

        // S'il y a des erreurs, les lancer
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Erreurs de validation :\n" + String.join("\n", errors));
        }

        // Enregistrer l'événement s'il n'y a pas d'erreurs
        return eventRep.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRep.save(event);
    }

    @Override
    public Event retrieveEvent(Long idEvent) {
        return eventRep.findById(idEvent).orElseThrow(() -> new RuntimeException("Event not Found"));
    }

    @Override
    public void removeEvent(Long idEvent) { eventRep.deleteById(idEvent); }

    @Override
    public List<Event> searchEventsByName(String nomEvent) {
        return eventRep.findByNomEventContainingIgnoreCase(nomEvent);
    }


    @Override
    public List<Event> findAllOrderByPrixEventDesc() {
        return eventRep.findAllOrderByPrixEventDesc();
    }
    @Override
    public List<Event> findByDateDebutEvent(LocalDate dateDebutEvent) {
        return eventRep.findByDateDebutEvent(dateDebutEvent);
    }

    @Override
    public byte[] generateQRCodeForEvent(Event event) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String startDateFormatted = event.getDateDebutEvent().format(dateFormatter);
        String endDateFormatted = event.getDateFinEvent().format(dateFormatter);

        // Générer le contenu du code QR avec les informations de l'événement
        String qrContent = "BEGIN:VCALENDAR\n" +
                "VERSION:2.0\n" +
                "BEGIN:VEVENT\n" +
                "SUMMARY:" + event.getNomEvent() + "\n" +
                "DTSTART:" + startDateFormatted + "\n" +
                "DTEND:" + endDateFormatted + "\n" +
                "LOCATION:" + event.getLieuEvent() + "\n" +
                "DESCRIPTION:" + event.getDescriptionEvent() + "\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";

        try {
            // Générer le code QR
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(qrContent, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            // Convertir le code QR en tableau d'octets et le renvoyer
            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace(); // Gérer les erreurs de génération du code QR
            return null;
        }
    }

     /* @Override
    public Event addEvent(Event event) {

        //champs non vide
        if (event.getNomEvent().isEmpty() || event.getDescriptionEvent().isEmpty() ||
                event.getOrganisateurEvent().isEmpty() || event.getLieuEvent().isEmpty() ||
                event.getImageEvent().isEmpty() || event.getDateDebutEvent() == null ||
                event.getDateFinEvent() == null) {
            throw new IllegalArgumentException("Tous les champs obligatoires doivent être renseignés.");
        }
        //prix et capacite non negatifs
        if (event.getPrixEvent() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas pas être négatif.");
        }
        if (event.getCapaciteEvent() < 0) {
            throw new IllegalArgumentException("La capacité ne peut pas pas être négative.");
        }
        //date de fin pas avant la date de début
        if (event.getDateFinEvent().isBefore(event.getDateDebutEvent())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début.");
        }

        //date de début pas avant la date actuelle
        if (event.getDateDebutEvent().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de début ne peut pas être antérieure à la date actuelle.");
        }
        return eventRep.save(event);
    }*/

}