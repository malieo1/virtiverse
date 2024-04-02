package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.serviceInterface.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Gestion Evenement")
@RestController
@RequestMapping("/Evenement")
@AllArgsConstructor
public class EventController {
    IEventService eventService;

    @GetMapping("/EventController")
    public List<Event> retrieveAllEvents() {
        return eventService.retrieveAllEvents();
    }
    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }
    @PutMapping("/updateEvent")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }
    @GetMapping("/getById/{id_event}")
    public Event retrieveEvent(@PathVariable ("id_event")Long id_event) {
        return eventService.retrieveEvent(id_event);
    }
    @DeleteMapping("/DeleteById/{id_event}")
    public void removeEvent(@PathVariable("id_event") Long id_event) {
        eventService.removeEvent(id_event);
    }
}
