package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.CartRepository;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.serviceInterface.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImp implements ICartService {

    CartRepository cartRepository;
    OurUserRepo ourUserRepo;

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


    public Cart getCartByUserId(Long userId) {
        Optional<User> userOptional = ourUserRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return cartRepository.findByUser(user).orElseThrow(() -> new NoSuchElementException("Cart not found for user with ID: " + userId));
        } else {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
    }
    @Override
    public Cart createCartForUser(Long id) {
        Optional<User> userOptional = ourUserRepo.findById(id);
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
            List<PubItem> pubItems = new ArrayList<>(cart.getPubItems());

            // Construct image URLs for each PubItem
            for (PubItem pubItem : pubItems) {
                constructImageUrl(pubItem);
            }

            return pubItems;
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



    public List<PubItem> getPubItemsInCartByUserId( Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found for user with ID: " + userId));

        List<PubItem> pubItems = new ArrayList<>(cart.getPubItems());

        // Construct image URLs for each PubItem
        for (PubItem pubItem : pubItems) {
            constructImageUrl(pubItem);
        }

        return pubItems;
    }

    private void constructImageUrl(PubItem pubItem) {
        String fileName = pubItem.getImage(); // Get the image filename from the PubItem
        try {
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8"); // URL encode the filename
            String imageUrl = "http://localhost:8082/img/" + encodedFileName.replace("+", "%20");
            pubItem.setImage(imageUrl); // Set the image URL back to PubItem
        } catch (UnsupportedEncodingException e) {
            // Handle encoding exception
            e.printStackTrace();
        }
    }
}
