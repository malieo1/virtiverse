package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    Event addEvent(Event event);
    Event updateEvent(Event event);
    Event retrieveEvent(Long idEvent);
    void removeEvent (Long idEvent);
   List<Event> searchEventsByName(String nomEvent);
  List<Event> findAllOrderByPrixEventDesc();
  List<Event> findByDateDebutEvent(LocalDate dateDebutEvent);
  byte[] generateQRCodeForEvent(Event event);
}