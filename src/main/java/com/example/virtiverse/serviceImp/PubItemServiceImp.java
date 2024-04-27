package com.example.virtiverse.serviceImp;


import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceInterface.IPubItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PubItemServiceImp implements IPubItemService {

    PubItemRepository pubItemRepository;
    UserRepository userRepository;
    @Override
    public PubItem addPubitem(@Valid PubItem pubItem) {
        if (pubItem.getDescription() == null || pubItem.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (pubItem.getPrix() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (String.valueOf(pubItem.getNumTelephone()).length() > 8) {
            throw new IllegalArgumentException("NumTelephone cannot exceed 6 numbers");
        }
        pubItem.setDatePost(new Date());
        return pubItemRepository.save(pubItem);
    }

    @Override
    public PubItem updatePubitem(@Valid PubItem pubItem) {

        if (pubItem.getPrix() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        return pubItemRepository.save(pubItem);
    }

    @Override
    public List<PubItem> getPubitem() {
        return pubItemRepository.findAll();
    }

    @Override
    public void deletePubitem(Long id_pub) {
        pubItemRepository.deleteById(id_pub);
    }


    @Override
    //public List<PubItem> searchPubItems(String keyword) {
       // return pubItemRepository.findByDescriptionContainingIgnoreCase(keyword);
    //}

    public List<PubItem> searchPubItems(String keyword) {
        return pubItemRepository.searchPubItems(keyword);
    }

    public List<PubItem> getPubItemsSortedByPrice() {
        Sort sortByPrice = Sort.by("prix").ascending();
        return pubItemRepository.findAll(sortByPrice);
    }


    public List<PubItem> filterByPriceRange(float minPrice, float maxPrice) {
        return pubItemRepository.findAll().stream()
                .filter(pubItem -> pubItem.getPrix() >= minPrice && pubItem.getPrix() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<PubItem> getPubItemsSortedByEtatAsc() {
        return pubItemRepository.findByOrderByEtatAsc();
    }

    public List<PubItem> getPubItemsSortedByEtatDesc() {
        return pubItemRepository.findAllByOrderByEtatDesc();
    }




    @Override
    public PubItem addPubitemm(@Valid PubItem pubItem, Integer id) {
        if (pubItem.getDescription() == null || pubItem.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (pubItem.getPrix() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (String.valueOf(pubItem.getNumTelephone()).length() > 8) {
            throw new IllegalArgumentException("NumTelephone cannot exceed 8 numbers");
        }
        // Find the user by userId
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Set the user for the pubItem
        pubItem.setUser(user);
        pubItem.setDatePost(new Date());

        return pubItemRepository.save(pubItem);
    }
}
