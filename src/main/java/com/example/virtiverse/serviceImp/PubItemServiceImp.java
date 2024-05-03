package com.example.virtiverse.serviceImp;


import com.example.virtiverse.entities.Etat;
import com.example.virtiverse.entities.FileUploadUtil;
import com.example.virtiverse.entities.PubItem;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.PubItemRepository;
import com.example.virtiverse.repository.UserRepository;
import com.example.virtiverse.serviceInterface.IPubItemService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Path;
import javax.validation.Valid;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PubItemServiceImp implements IPubItemService {

    PubItemRepository pubItemRepository;
    UserRepository userRepository;
    @Override
    public PubItem addPubitem(@Valid PubItem pubItem , MultipartFile multipartFile , Integer id) throws IOException {
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





    @Override
    public PubItem updatePubitem(@Valid PubItem pubItem) {

        if (pubItem.getPrix() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
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

    @Override
    public PubItem getPubItemById(Long id) {
        return null;
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

}