package com.example.virtiverse.controller;

import com.example.virtiverse.dto.ReqRes;
import com.example.virtiverse.entities.User;
import com.example.virtiverse.serviceImp.AuthService;
import com.example.virtiverse.serviceImp.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AdminUser {
    @Autowired
    UserService userService ;
    private final ObjectMapper objectMapper;
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
    User addImageToUser(@RequestPart("image") MultipartFile multipartFile , @RequestParam("id") Long id) throws IOException {
        return userService.addImageToUser(multipartFile,id);

    }


    @GetMapping("/admin/getUserById/{id}")
    User getUserById(@PathVariable("id")Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    void deleteUser(@PathVariable("id") Long id){
        userService.deletUser(id);

    }








    @PostMapping("/public/addUser")
    User addUser(@RequestPart("image") MultipartFile multipartFile , @RequestParam("user") String user) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user1 = objectMapper.readValue(user, User.class);
        System.out.println(user1);
        return userService.addUser(user1 , multipartFile);

    }
    @PostMapping("/public/update")
    User updateUser(@RequestPart("image") MultipartFile multipartFile , @RequestParam("user") String user) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user1 = objectMapper.readValue(user, User.class);
        System.out.println(user1);
        return userService.updateUser(user1 , multipartFile);

    }
    @GetMapping("/admin/getUsers")
    List<User> getUsers(){
        return userService.getusers();
    }

}