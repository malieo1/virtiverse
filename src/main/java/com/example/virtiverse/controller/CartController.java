package com.example.virtiverse.controller;


import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceInterface.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    ICartService iCartService;
    private UserRepository userRepository;
    PubItemRepository pubItemRepository;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        Optional<Cart> cart = iCartService.getCartById(cartId);
        return cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user/{userName}")
    public ResponseEntity<Cart> createCartForUser(@PathVariable String userName) {
        Cart cart = iCartService.createCartForUser(userName);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<String> addItemToCart(@PathVariable Long cartId, @RequestParam Long itemId) {
        Optional<PubItem> pubItemOptional = pubItemRepository.findById(itemId);
        if (pubItemOptional.isPresent()) {
            PubItem pubItem = pubItemOptional.get();
            iCartService.addItemToCart(cartId, pubItem);
            return new ResponseEntity<>("PubItem added to cart", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("PubItem with ID " + itemId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cartId}/items/{id_pub}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long id_pub) {
        iCartService.removeItemFromCart(cartId, id_pub);
        return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
    }
}
