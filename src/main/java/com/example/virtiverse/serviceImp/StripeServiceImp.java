package com.example.virtiverse.serviceImp;

import com.example.virtiverse.serviceInterface.IStripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StripeServiceImp implements IStripeService {

    private final Environment env;


    @Autowired
    public StripeServiceImp(Environment env) {
        this.env = env;
    }

    @Override
    public String createPaymentIntent(BigDecimal amount, String currency) {
        String stripeApiKey = env.getProperty("stripe.api.key");
        Stripe.apiKey = stripeApiKey;
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount.longValue())
                .setCurrency(currency)
                .build();

        try {
            PaymentIntent intent = PaymentIntent.create(params);
            return intent.getClientSecret();
        } catch (StripeException e) {
            // Handle Stripe API errors
            e.printStackTrace();
            return null;
        }
    }
}

