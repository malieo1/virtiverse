package com.example.virtiverse.serviceImp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;



@Service
@Slf4j
public class SmsService {

    @Value("AC5936d772240f1ff1fb44d0e68f492694")
    private String ACCOUNT_SID;

    @Value("1044cd37cd1e97ff133b9feb70ef9d6c")
    private String AUTH_TOKEN;

    @Value("+19387661632")
    private String FROM_NUMBER;

    public void sendSms(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber("+21658404108"), // To number
                new PhoneNumber(FROM_NUMBER), // From number
                message // SMS body
        ).create();
    }

}
