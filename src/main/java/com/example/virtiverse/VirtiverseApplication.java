package com.example.virtiverse;

import com.example.virtiverse.serviceImp.AuthService;
import com.example.virtiverse.serviceImp.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.virtiverse", "com.example.virtiverse.configuration"})
public class VirtiverseApplication {
    public static void main(String[] args) {
        SpringApplication.run(VirtiverseApplication.class, args);
    }

}
