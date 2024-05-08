package com.example.virtiverse.controller;

import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.serviceInterface.LostandFoundInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequestMapping("/LostandFound")
@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")

    public class LostandFoundController {

    LostandFoundInterface lostandFoundInterface;

        @GetMapping("/retrieveAllPubs")
        public List<LostandFound> retrieveAllPubs() {
            return lostandFoundInterface.retrieveAllPubs();

        }

        @PutMapping("/updatePub")
        public LostandFound updatePub(@RequestBody LostandFound pub) {
            return lostandFoundInterface.updatePub(pub);
        }

        @PostMapping("/addPub/{id}")
        public LostandFound addPub(@RequestParam("lostandfound") String pub , @RequestParam("file") MultipartFile file , @PathVariable("id") long id ) throws JsonProcessingException {
            LostandFound lostAndFound = new ObjectMapper().readValue(pub, LostandFound.class);
            log.info(pub.toString());
            log.info(file.getName());
            return lostandFoundInterface.addPub(lostAndFound, file , id );
        }

        @DeleteMapping("/removePub/{idpub}")
        public void removePub(@PathVariable("idpub") Long idpub) {
        lostandFoundInterface.removePublicationWithMessages(idpub);
    }

        @GetMapping("/retrievepub/{idpub}")
        public LostandFound retrievepub(@PathVariable("idpub") Long idpub) {
        return lostandFoundInterface.retrievepub(idpub);
    }

        @GetMapping("/retrievebyStatus/{status}")
        public List<LostandFound> retrievebyStatus(@PathVariable("status") String status) {
        return lostandFoundInterface.retrievebyStatus(status);
    }

        @GetMapping("/retrievebyName/{name}")
        public List<LostandFound> retrievebyName(@PathVariable("name") String name) {
        return lostandFoundInterface.retrievebyName(name);
    }

    @GetMapping("/retrievebyUser/{id}")
    public List<LostandFound> retrievebyUser(@PathVariable("id") long id) {
        return lostandFoundInterface.retrievebyUser(id);
    }

    @PutMapping("/changestatus/{idpub}")
    public LostandFound changestatus(@PathVariable("idpub") long idpub) {
        return lostandFoundInterface.changestatus(idpub);
    }
}
