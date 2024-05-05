package com.example.virtiverse.serviceImp;


import com.example.virtiverse.entities.Etat;
import com.example.virtiverse.entities.FileUploadUtil;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.serviceInterface.IPubItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PubItemServiceImp implements IPubItemService {

    PubItemRepository pubItemRepository;
    OurUserRepo userRepository;
    @Override
    public PubItem addPubitem(@Valid PubItem pubItem , MultipartFile multipartFile , Long id) throws IOException {
        if (pubItem.getDescription() == null || pubItem.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (pubItem.getPrix() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (String.valueOf(pubItem.getNumTelephone()).length() > 8) {
            throw new IllegalArgumentException("NumTelephone cannot exceed 6 numbers");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        pubItem.setUser(user);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        pubItem.setImage(fileName);
        String uploadDir = "C:/xampp/htdocs/img/" ;
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        pubItem.setDatePost(new Date());
        System.out.println("uploadDir: " + uploadDir); // Check the directory path
        System.out.println("fileName: " + fileName);

        pubItem.setDatePost(new Date());
        return pubItemRepository.save(pubItem);
    }






    public PubItem updatePubitem(@Valid PubItem updatedPubItem) {
        // Find the existing item by ID
        PubItem existingItem = pubItemRepository.findById(updatedPubItem.getId_pub())
                .orElseThrow(() -> new IllegalArgumentException("PubItem not found"));

        // Update only the fields that are provided in the updatedPubItem
        existingItem.setName(updatedPubItem.getName());
        existingItem.setDescription(updatedPubItem.getDescription());
        existingItem.setPrix(updatedPubItem.getPrix());
        existingItem.setNumTelephone(updatedPubItem.getNumTelephone());
        existingItem.setEtat(updatedPubItem.getEtat());

        // Save the updated item
        return pubItemRepository.save(existingItem);
    }   public PubItem updatePubItem(@Valid PubItem pubItem) {
        // Perform validation if needed
        return pubItemRepository.save(pubItem);
    }


    @Override
    public List<PubItem> getPubitem() {
        List<PubItem> pubItems = pubItemRepository.findAll();
        pubItems.forEach(this::constructImageUrl);
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

    @Override
    public void deletePubitem(Long id_pub) {
        pubItemRepository.deleteById(id_pub);
    }


    @Override
    //public List<PubItem> searchPubItems(String keyword) {
    // return pubItemRepository.findByDescriptionContainingIgnoreCase(keyword);
    //}

    public List<PubItem> searchPubItems(String keyword) {
        List<PubItem> pubItems = pubItemRepository.searchPubItems(keyword);
        pubItems.forEach(this::constructImageUrl); // Construct image URL for each item
        return pubItems;
    }
    public List<PubItem> getPubItemsSortedByPriceAS() {
        Sort sortByPrice = Sort.by("prix").ascending();
        List<PubItem> pubItems = pubItemRepository.findAll(sortByPrice);
        pubItems.forEach(this::constructImageUrl); // Construct image URL for each item
        return pubItems;
    }
    public List<PubItem> getPubItemsSortedByPriceDS() {
        Sort sortByPrice = Sort.by("prix").descending();
        List<PubItem> pubItems =  pubItemRepository.findAll(sortByPrice);
        pubItems.forEach(this::constructImageUrl); // Construct image URL for each item
        return pubItems;
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
    public PubItem addPubitemm(@Valid PubItem pubItem, Long id) {
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

    @Override
    public Optional<PubItem> getPubItemById(Long id_pub) {
        Optional<PubItem> pubItemOptional = pubItemRepository.findById(id_pub);
        pubItemOptional.ifPresent(this::constructImageUrl); // If PubItem exists, construct image URL
        return pubItemOptional;
    }


    public List<PubItem> getItemsSortedByEtat(Etat etat) {
        List<PubItem> items;
        switch (etat) {
            case excellente:
                items = pubItemRepository.findByEtatOrderByDatePostDesc(Etat.excellente); // Implement sorting logic for excellente
                break;
            case bien:
                items = pubItemRepository.findByEtatOrderByDatePostDesc(Etat.bien); // Implement sorting logic for bien
                break;
            case moyenne:
                items = pubItemRepository.findByEtatOrderByDatePostDesc(Etat.moyenne); // Implement sorting logic for moyenne
                break;
            case mauvaise:
                items = pubItemRepository.findByEtatOrderByDatePostDesc(Etat.mauvaise); // Sort items with "mauvaise" Etat by date of posting in descending order
                break;
            default:
                items = new ArrayList<>(); // Handle other cases if needed
        }
        return items;
    }

    public List<PubItem> getPubItemsByUserId(Long userId) {
        List<PubItem> pubItems = pubItemRepository.findByUserId(userId);
        pubItems.forEach(this::constructImageUrl); // Construct image URL for each item
        return pubItems;
    }


    public List<PubItem> getPubItemsByPrice(float price) {
        List<PubItem> pubItemsWithSamePrice = pubItemRepository.findByPrix(price);
        pubItemsWithSamePrice.forEach(this::constructImageUrl); // Construct image URL for each item
        return pubItemsWithSamePrice;
    }

}