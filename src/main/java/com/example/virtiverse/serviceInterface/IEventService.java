package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    List<Event> retrieveAllEventsByUser(Long id);
    List<Event> retrieveApprovedEvents();
    Event addEvent(Event event, Long id);
    Event approveEvent(Long idEvent);
    public Event RejectEvent(Long idEvent);
    Event updateEvent(Long idEvent, Event updatedEvent);
    Event retrieveEvent(Long idEvent);
    void removeEvent (Long idEvent);
   List<Event> searchEventsByOrganisateur(String nomEvent);
  List<Event> findAllOrderByPrixEventDesc();
  List<Event> findByDateDebutEvent(LocalDate dateDebutEvent);
  byte[] generateQRCodeForEvent(Event event);
}
