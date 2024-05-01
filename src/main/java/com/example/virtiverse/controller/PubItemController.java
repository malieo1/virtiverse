package com.example.virtiverse.controller;

import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.serviceInterface.IPubItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
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

    @GetMapping("/sorted-by-price")
    public ResponseEntity<List<PubItem>> getPubItemsSortedByPrice() {
        List<PubItem> pubItems = iPubItemService.getPubItemsSortedByPrice();
        return new ResponseEntity<>(pubItems, HttpStatus.OK);
    }

    @GetMapping("/filterByPrice")
    public ResponseEntity<List<PubItem>> filterByPriceRange(
            @RequestParam float minPrice,
            @RequestParam float maxPrice) {
        List<PubItem> filteredItems = iPubItemService.filterByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(filteredItems, HttpStatus.OK);
    }

    @GetMapping("/sortByEtatAsc")
    public List<PubItem> getPubItemsSortedByEtatAsc() {
        return iPubItemService.getPubItemsSortedByEtatAsc();
    }

    @GetMapping("/sortByEtatDesc")
    public List<PubItem> getPubItemsSortedByEtatDesc() {
        return iPubItemService.getPubItemsSortedByEtatDesc();
    }

    @PostMapping ("/addPubItemm")
    public ResponseEntity<PubItem> addPubItemm(@Valid @RequestBody PubItem pubItem, @RequestParam Integer id) {
        PubItem addedPubItem = iPubItemService.addPubitemm(pubItem, id);
        return new ResponseEntity<>(addedPubItem, HttpStatus.CREATED);
    }


}