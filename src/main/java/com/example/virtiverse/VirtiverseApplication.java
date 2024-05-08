package com.example.virtiverse;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.virtiverse", "com.example.virtiverse.config"})
public class VirtiverseApplication {

    public static void main(String[] args) {

        SpringApplication.run(VirtiverseApplication.class, args);



    }

}