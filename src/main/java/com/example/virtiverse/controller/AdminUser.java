package com.example.virtiverse.controller;

import com.example.virtiverse.entities.User;
import com.example.virtiverse.serviceImp.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class AdminUser {
    @Autowired
    UserService userService ;
    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("USers alone can access this ApI only");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi(){
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }

    /** You can use this to get the details(name,email,role,ip, e.t.c) of user accessing the service*/
    @GetMapping("/public/email")
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication); //get all details(name,email,password,roles e.t.c) of the user
        System.out.println(authentication.getDetails()); // get remote ip
        System.out.println(authentication.getName()); //returns the email because the email is the unique identifier
        return authentication.getName(); // returns the email
     }

    @PostMapping("/public/addimage")
    User addImageToUser(@RequestPart("image") MultipartFile multipartFile , @RequestParam("id") Integer id) throws IOException {
        return userService.addImageToUser(multipartFile,id);

    }


    @GetMapping("/public/getUserById/{id}")
    User getUserById(@PathVariable("id")Integer id){
        return userService.getUserById(id);
    }


    @GetMapping("/public/getUsers")
    List<User> getUsers(){
        return userService.getusers();
    }

}
