package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface userImp {
    User addUser(User user , MultipartFile multipartFile) throws IOException;
    User updateUser(User user , MultipartFile multipartFile) throws IOException;
    void deletUser(Long Id);
    User getUserById(Long id );
    List<User> getusers();
    User addImageToUser(MultipartFile multipartFile ,Long id) throws IOException;
}
