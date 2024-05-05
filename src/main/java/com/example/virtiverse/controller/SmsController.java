package com.example.virtiverse.controller;

import com.example.virtiverse.serviceImp.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SmsController {
    SmsService smsService;
@GetMapping("/SendSms")
    public void sendSms(String to, String message) {
        smsService.sendSms(to, "Virtiverse");
    }
}
