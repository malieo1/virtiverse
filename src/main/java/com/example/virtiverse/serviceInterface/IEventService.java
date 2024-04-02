package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Event;

import java.util.List;

public interface IEventService {
    List<Event> retrieveAllEvents();
    Event addEvent(Event event);
    Event updateEvent(Event event);
    Event retrieveEvent(Long id_event);
    void removeEvent (Long id_event);
}
