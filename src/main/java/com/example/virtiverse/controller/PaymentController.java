package com.example.virtiverse.controller;

import com.example.virtiverse.serviceInterface.IStripeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {
    IStripeService iStripeService;


    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestParam BigDecimal amount,
                                                      @RequestParam String currency) {
        String clientSecret = iStripeService.createPaymentIntent(amount, currency);
        if (clientSecret != null) {
            return ResponseEntity.ok(clientSecret);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment intent");
        }
    }
}
