package com.example.virtiverse.controller;

import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.serviceInterface.IPubItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pubitem")
@AllArgsConstructor
public class PubItemController {
    IPubItemService iPubItemService;

    @GetMapping("/retrieveAllPub")
    public List<PubItem> retrieveAllPub() {
        return iPubItemService.getPubitem();
    }


    @PostMapping("/addPub")
    public PubItem addPubitem(@RequestBody PubItem pubItem) {
        return iPubItemService.addPubitem(pubItem);
    }

    @PostMapping("/updatePub")
    public PubItem updatePubitem(@RequestBody PubItem pubItem) {
        return iPubItemService.updatePubitem(pubItem);
    }

    @DeleteMapping("/removePub/{id}")
    public void deletePubitem(@PathVariable("id") Long id_pub) {
        iPubItemService.deletePubitem(id_pub);
    }

    @GetMapping("/searchPubItems")
    public ResponseEntity<List<PubItem>> searchPubItems(@RequestParam("keyword") String keyword) {
        List<PubItem> searchResults = iPubItemService.searchPubItems(keyword);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
