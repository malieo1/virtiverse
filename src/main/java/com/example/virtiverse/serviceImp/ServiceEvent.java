package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.repository.EventRep;
import com.example.virtiverse.serviceInterface.IEventService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

}
