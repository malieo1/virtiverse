package com.example.virtiverse.serviceInterface;

import java.math.BigDecimal;

public interface IStripeService {
    String createPaymentIntent(BigDecimal amount, String currency);

}
