package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.PubItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICartService {

    Optional<Cart> getCartById(Long id);

    Cart createCart(Cart cart);


    void addItemToCart(Long cartId, PubItem pubItem);

    Cart createCartForUser(Long id);

    void removeItemFromCart(Long cartId, Long itemId);

    List<PubItem> getProductsInCart(Long cartId);

    List<Cart> getAllCartsWithProducts();

    void deleteCart (Long id);

    List<Cart> searchCarts(Long id, BigDecimal total, String pubItems);

    Cart getCartByUserId(Long id);

    List<PubItem> getPubItemsInCartByUserId(Long userId);
}

