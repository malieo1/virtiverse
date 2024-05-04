package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceInterface.userImp;
import com.example.virtiverse.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Service

public class UserService implements userImp {

@Autowired
    OurUserRepo ourUserRepo;

    @Override
    public User getUserById(Integer id) {
        User user = ourUserRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        constructImageUrl(user);
        return user;

    }

    @Override
    public List<User> getusers() {
        List<User> users  = ourUserRepo.findAll();
       users.forEach(this::constructImageUrl);
        return users;
    }


    @Override
    public User addImageToUser(MultipartFile multipartFile, Integer id) throws IOException {
        User user = ourUserRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "C:/xampp/htdocs/img/" ;
    user.setImage(fileName);
        ourUserRepo.save(user);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return user;
    }

    private void constructImageUrl(User user) {
        String fileName = user.getImage(); // Get the image filename from the user
        if (fileName != null) {
            try {
                String encodedFileName = URLEncoder.encode(fileName, "UTF-8"); // URL encode the filename
                String imageUrl = "http://localhost:8082/img/" + encodedFileName.replace("+", "%20");
                user.setImage(imageUrl); // Set the image URL back to user
            } catch (UnsupportedEncodingException e) {
                // Handle encoding exception
                e.printStackTrace();
            }
        }
    }
}
