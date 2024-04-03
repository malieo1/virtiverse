package com.example.virtiverse.controller;

import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.serviceInterface.LostandFoundInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/LostandFound")
@RestController
@AllArgsConstructor


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

    @PostMapping("/addPub")
    public LostandFound addPub(@RequestBody LostandFound pub) {
        return lostandFoundInterface.addPub(pub);
    }

    @DeleteMapping("/removePub/{idpub}")
    public void removePub(@PathVariable("idpub") Long idpub) {
        lostandFoundInterface.removePub(idpub);
    }

    @GetMapping("/retrievepub/{idpub}")
    public LostandFound retrievepub(@PathVariable("idpub") Long idpub) {
        return lostandFoundInterface.retrievepub(idpub);
    }
}
