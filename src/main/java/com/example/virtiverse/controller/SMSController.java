package com.example.virtiverse.controller;

import com.example.virtiverse.entities.SMSRequest;
import com.example.virtiverse.serviceImp.SMSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController {

    private final SMSServiceImpl smsService;

    @Autowired
    public SMSController(SMSServiceImpl smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send-sms")
    public String sendSMS(@RequestBody SMSRequest smsRequest) {
        try {
            smsService.sendSMS("+21693661180", "Vous avez une nouvelle demande");
            return "SMS sent successfully";
        } catch (Exception e) {
            return "Failed to send SMS: " + e.getMessage();
        }
    }
}
