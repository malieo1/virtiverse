package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Messages;
import com.example.virtiverse.serviceInterface.MessageInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/message")
@RestController
@AllArgsConstructor
public class MessageController {
    MessageInterface messageInterface;

    @GetMapping("/retrieveAllMessages")
    public List<Messages> retrieveAllMessages() {
        return messageInterface.retrieveAllMessages();
    }

    @PostMapping("addMessage")
    public Messages addMessage(@RequestBody Messages messages) {
        return messageInterface.addMessage(messages);
    }

    @DeleteMapping("/removemessage/{idmessage}")
    public void removemessage(@PathVariable("idmessage") Long idmessage) {
        messageInterface.removemessage(idmessage);
    }

    @GetMapping("/retrievemessage/{idmessage}")
    public Messages retrievemessage(@PathVariable("idmessage") Long idmessage) {
        return messageInterface.retrievemessage(idmessage);
    }

    @PutMapping("/updatemessage")
    public Messages updatemessage(@RequestBody Messages messages) {
        return messageInterface.updatemessage(messages);
    }
}
