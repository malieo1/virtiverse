package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.serviceInterface.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public Event retrieveEvent(@PathVariable ("id_event") Long id_event) {
        return eventService.retrieveEvent(id_event);
    }
    @DeleteMapping("/DeleteById/{id_event}")
    public void removeEvent(@PathVariable("id_event") Long id_event) {
        eventService.removeEvent(id_event);
    }
    @GetMapping("/searchEventsByName/{nom_event}")
    public List<Event> searchEventsByName(@PathVariable String nom_event) {
        return eventService.searchEventsByName(nom_event);
    }
    @GetMapping("/orderByPriceDesc")
    public List<Event> findAllOrderByPrixDesc() {
        return eventService.findAllOrderByPrixDesc();
    }


    @GetMapping("/findByStartDateEquals/{date}")
    public List<Event> findByDateDebut_eventEquals(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return eventService.findByDateDebut_eventEquals(date);
    }
}
