package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.Commentaire;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;

import java.util.Optional;

public interface ICartService {

    Optional<Cart> getCartById(Long id);

    Cart createCart(Cart cart);


    void addItemToCart(Long cartId, PubItem pubItem);

    Cart createCartForUser(Integer id);

    void removeItemFromCart(Long cartId, Long itemId);
}
