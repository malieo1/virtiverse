package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.CartRepository;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceInterface.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    public Cart createCartForUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("User not found with username: " + id);
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


    public List<PubItem> getProductsInCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            return new ArrayList<>(cart.getPubItems());
        } else {
            throw new IllegalArgumentException("Cart not found with id: " + cartId);
        }
    }


    @Override
    public List<Cart> getAllCartsWithProducts() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getPubItems().clear(); // Detach products from the cart
            cartRepository.delete(cart); // Delete the cart
        } else {
            throw new IllegalArgumentException("Cart not found with id: " + cartId);
        }
    }


    public List<Cart> searchCarts(Long cartId, BigDecimal total, String itemName) {
        return cartRepository.findByCriteria(cartId, total, itemName);
    }

}
