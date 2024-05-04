package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface userImp {
    User getUserById(Integer id );
    List<User> getusers();
    User addImageToUser(MultipartFile multipartFile ,Integer id) throws IOException;
}
