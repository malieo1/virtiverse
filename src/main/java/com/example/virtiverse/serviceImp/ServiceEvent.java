package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.repository.EventRep;
import com.example.virtiverse.serviceInterface.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Event retrieveEvent(Long id_event) {
        return eventRep.findById(id_event).orElse(null);
    }

    @Override
    public void removeEvent(Long id_event) { eventRep.deleteById(id_event); }

}
