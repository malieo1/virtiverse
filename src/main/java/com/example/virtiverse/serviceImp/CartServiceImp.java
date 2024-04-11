package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.Commentaire;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.CartRepository;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceInterface.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImp implements ICartService {

    CartRepository cartRepository;
    UserRepository userRepository;

    PubItemRepository pubItemRepository;
    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart createCart(Cart cart) {
        return null;
    }

    @Override
    public void addItemToCart(Long cartId, PubItem pubItem) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getPubItems().add(pubItem);

            BigDecimal total = cart.getPubItems().stream()
                    .map(PubItem::getPrix)
                    .map(BigDecimal::valueOf)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            cart.setTotal(total);

            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with id: " + cartId);
        }
    }


    @Override
    public Cart createCartForUser(String userName) {
        Optional<User> userOptional = userRepository.findById(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("User not found with username: " + userName);
        }
    }

    @Override
    public void removeItemFromCart(Long cartId, Long id_pub) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getPubItems().removeIf(item -> item.getId_pub().equals(id_pub));

            BigDecimal total = cart.getPubItems().stream()
                    .map(PubItem::getPrix)
                    .map(BigDecimal::valueOf)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            cart.setTotal(total);

            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with id: " + cartId);
        }
    }


}
