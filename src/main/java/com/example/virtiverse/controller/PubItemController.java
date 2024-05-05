package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Etat;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.serviceInterface.IPubItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/pubitem")
@AllArgsConstructor

public class PubItemController {
    IPubItemService iPubItemService;



    private final ObjectMapper objectMapper;


    @GetMapping("/retrieveAllPub")
    public List<PubItem> retrieveAllPub() {
        return iPubItemService.getPubitem();
    }


    @PostMapping("/addPub")
    public PubItem addPubitem( @RequestPart("pubitem") String pubItem ,   @RequestPart("image") MultipartFile multipartFile , @RequestParam("id") Long id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PubItem pubItemObject = objectMapper.readValue(pubItem, PubItem.class);
        System.out.println(pubItemObject);
        return iPubItemService.addPubitem(pubItemObject , multipartFile ,id);
    }

    @PostMapping("/updatePub")
    public ResponseEntity<PubItem> updatePubItem(@Valid @RequestBody PubItem updatedPubItem) {
        PubItem updatedItem = iPubItemService.updatePubitem(updatedPubItem);
        return ResponseEntity.ok(updatedItem);
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

    @GetMapping("/sorted-by-priceas")
    public ResponseEntity<List<PubItem>> getPubItemsSortedByPriceAS() {
        List<PubItem> pubItems = iPubItemService.getPubItemsSortedByPriceAS();
        return new ResponseEntity<>(pubItems, HttpStatus.OK);
    }

    @GetMapping("/sorted-by-priceds")
    public ResponseEntity<List<PubItem>> getPubItemsSortedByPriceDS() {
        List<PubItem> pubItems = iPubItemService.getPubItemsSortedByPriceDS();
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
    public ResponseEntity<PubItem> addPubItemm(@Valid @RequestBody PubItem pubItem, @RequestParam Long id) {
        PubItem addedPubItem = iPubItemService.addPubitemm(pubItem, id);
        return new ResponseEntity<>(addedPubItem, HttpStatus.CREATED);
    }

    @GetMapping("/itemsbyetat")
    public List<PubItem> getItemsSortedByEtat(@RequestParam Etat etat) {
        return iPubItemService.getItemsSortedByEtat(etat);
    }

    @GetMapping("/{id_pub}")
    public ResponseEntity<PubItem> getProductById(@PathVariable("id_pub") Long id_pub) {
        Optional<PubItem> product = iPubItemService.getPubItemById(id_pub);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<PubItem> getPubItemsByUserId(@PathVariable Long userId) {
        return iPubItemService.getPubItemsByUserId(userId);
    }


    @GetMapping("/byPrice/{price}")
    public ResponseEntity<List<PubItem>> getPubItemsByPrice(@PathVariable float price) {
        List<PubItem> pubItemsWithSamePrice = iPubItemService.getPubItemsByPrice(price);
        return new ResponseEntity<>(pubItemsWithSamePrice, HttpStatus.OK);
    }

}