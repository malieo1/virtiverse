package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.repository.OurUserRepo;
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
    OurUserRepo userRep;
    @GetMapping("/EventController")
    public List<Event> retrieveAllEvents() {
        return eventService.retrieveAllEvents();
    }
    @GetMapping("/approvedEvents")
    public List<Event> retrieveApprovedEvents() {
        return eventService.retrieveApprovedEvents();
    }

    @PutMapping("/approveEvent/{idEvent}")
    public Event approveEvent(@PathVariable Long idEvent) {
            Event approvedEvent = eventService.approveEvent(idEvent);
          //  emailEventService.sendSimpleMessage();
        return approvedEvent;
    }
    @PutMapping("/rejectEvent/{idEvent}")
    public Event RejectEvent(@PathVariable Long idEvent) {
        Event rejectedEvent = eventService.RejectEvent(idEvent);
        //  emailEventService.sendSimpleMessage();
        return rejectedEvent;
    }

    @GetMapping("/getEventsUser/{id}")
    public List<Event> retrieveAllEventsByUser(@PathVariable Long id) {
        return eventService.retrieveAllEventsByUser(id);
    }

    @PostMapping("/addEvent/{id}")
    public Event addEvent(@RequestBody Event event, @PathVariable Long id) {
            Event addedEvent = eventService.addEvent(event,id);
          return  addedEvent;
    }
@PutMapping("/updateEvent/{idEvent}")
    public Event updateEvent(@PathVariable Long idEvent, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(idEvent, updatedEvent);
    }

    @GetMapping("/getById/{idEvent}")
    public Event retrieveEvent(@PathVariable ("idEvent") Long idEvent) {
        return eventService.retrieveEvent(idEvent);
    }
    @DeleteMapping("/DeleteById/{idEvent}")
    public void removeEvent(@PathVariable("idEvent") Long idEvent) {
        eventService.removeEvent(idEvent);
    }
   @GetMapping("/searchEventsByOrganisateur/{organisateurEvent}")
    public List<Event> searchEventsByOrganisateur(@PathVariable String organisateurEvent) {
        return eventService.searchEventsByOrganisateur(organisateurEvent);
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
