package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.repository.UserRep;
import com.example.virtiverse.serviceInterface.IEmailEventService;
import com.example.virtiverse.serviceInterface.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Tag(name = "Gestion Evenement")
@RestController
@RequestMapping("/Evenement")
@AllArgsConstructor
@CrossOrigin("*")
public class EventController {
    IEventService eventService;
    IEmailEventService emailEventService;
    UserRep userRep;
    @GetMapping("/EventController")
    public List<Event> retrieveAllEvents() {
        return eventService.retrieveAllEvents();
    }
    @GetMapping("/approvedEvents")
    public List<Event> retrieveApprovedEvents() {
        return eventService.retrieveApprovedEvents();
    }

    @PutMapping("/approveEvent/{idEvent}")
    public ResponseEntity<String> approveEvent(@PathVariable Long idEvent) {
        try {
            Event approvedEvent = eventService.approveEvent(idEvent);
          //  emailEventService.sendSimpleMessage();
            return ResponseEntity.ok("Evènement approuvé avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addEvent/{userName}")
    public ResponseEntity<String> addEvent(@RequestBody Event event, @PathVariable String userName) {
        try {
            Event addedEvent = eventService.addEvent(event,userName);
            return ResponseEntity.ok("Evenement ajouté avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }
    @GetMapping("/getById/{idEvent}")
    public Event retrieveEvent(@PathVariable ("idEvent") Long idEvent) {
        return eventService.retrieveEvent(idEvent);
    }
    @DeleteMapping("/DeleteById/{idEvent}")
    public void removeEvent(@PathVariable("idEvent") Long idEvent) {
        eventService.removeEvent(idEvent);
    }
   @GetMapping("/searchEventsByName/{nomEvent}")
    public List<Event> searchEventsByName(@PathVariable String nomEvent) {
        return eventService.searchEventsByName(nomEvent);
    }

    @GetMapping("/orderByPriceDesc")
    public List<Event> findAllOrderByPrixEventDesc() {
        return eventService.findAllOrderByPrixEventDesc();
    }


    @GetMapping("/findByDateDebutEvent/{dateDebutEvent}")
    public List<Event> findByDateDebutEvent(@PathVariable("dateDebutEvent") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebutEvent) {
        return eventService.findByDateDebutEvent(dateDebutEvent);
    }
    @GetMapping("/qrCode/{idEvent}")
    public ResponseEntity<byte[]> generateQRCodeForEvent(@PathVariable long idEvent) {
        Event event = eventService.retrieveEvent(idEvent);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        // Générer le code QR pour l'événement
        byte[] qrCodeBytes = eventService.generateQRCodeForEvent(event);
        if (qrCodeBytes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Retourner le code QR en tant que réponse avec le type de contenu approprié
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeBytes);
    }
}
