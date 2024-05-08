package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.User;
import com.example.virtiverse.repository.OurUserRepo;
import com.example.virtiverse.serviceInterface.userImp;
import com.example.virtiverse.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Service

public class UserService implements userImp {

@Autowired
    OurUserRepo ourUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User addUser(User user , MultipartFile multipartFile) throws IOException {

        User ourUsers = new User();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "C:/xampp/htdocs/img/" ;

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


        ourUsers.setEmail(user.getEmail());
        ourUsers.setPassword(passwordEncoder.encode(user.getPassword()));
        ourUsers.setRole(user.getRole());
        ourUsers.setImage(fileName);        ourUsers.setName(user.getName());
        ourUsers.setPhoneNumber(user.getPhoneNumber());
        ourUserRepo.save(ourUsers);




        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return user;
    }

    @Override
    public User updateUser(User user , MultipartFile multipartFile) throws IOException {
        System.out.println();
        User user1 = ourUserRepo.getById(user.getId());

        System.out.println("hedhaa userr");
        System.out.println(user1);

        String oldFileName = user1.getImage();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "C:/xampp/htdocs/img/" ;
        user.setImage(fileName);
      if(user1.getImage() != null && !user1.getImage().equals(user.getImage())) {

          File oldFile = new File(uploadDir + oldFileName);
          oldFile.delete(); // Delete

          user.setImage(fileName);
          FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);


      }
        ourUserRepo.save(user);
        return user;
    }

    @Override
    public void deletUser(Long Id) {
        User user = ourUserRepo.getById(Id);
        String oldFileName = user.getImage();

        // Delete the old image file
        if (oldFileName != null) {
            String uploadDir = "C:/xampp/htdocs/img/";
            File oldFile = new File(uploadDir + oldFileName);
            oldFile.delete(); // Delete the old image file
        }
        ourUserRepo.deleteById(Id);
    }


    @Override
    public User getUserById(Long id) {
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
    public User addImageToUser(MultipartFile multipartFile, Long id) throws IOException {
        User user = ourUserRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String oldFileName = user.getImage();

        // Delete the old image file
        if (oldFileName != null) {
            String uploadDir = "C:/xampp/htdocs/img/";
            File oldFile = new File(uploadDir + oldFileName);
            oldFile.delete(); // Delete the old image file
        }


        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "C:/xampp/htdocs/img/" ;
    user.setImage(fileName);
        ourUserRepo.save(user);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return user;
    }

    void constructImageUrl(User user) {
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
