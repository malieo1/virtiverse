package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    Event addEvent(Event event);
    Event updateEvent(Event event);
    Event retrieveEvent(Long id_event);
    void removeEvent (Long id_event);
    List<Event> searchEventsByName(String nom_event);
    List<Event> findAllOrderByPrixDesc();
    List<Event> findByDateDebut_eventEquals(LocalDate dateDebut);
}
