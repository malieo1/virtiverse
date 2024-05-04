package com.example.virtiverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.virtiverse", "com.example.virtiverse.configuration"})
public class VirtiverseApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtiverseApplication.class, args);
    }

}
