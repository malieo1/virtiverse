package com.example.virtiverse.controller;

import com.example.virtiverse.entities.Messages;
import com.example.virtiverse.serviceInterface.MessageInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/message")
@RestController
@AllArgsConstructor
public class MessageController {
    MessageInterface messageInterface;

    @GetMapping("/retrieveAllMessages")
    public List<Messages> retrieveAllMessages() {
        return messageInterface.retrieveAllMessages();
    }

    @PostMapping("addMessage/{idpub}/{iduser}")
    public Messages addMessage(@RequestBody Messages messages,@PathVariable("idpub")Long idpub ,@PathVariable("iduser")Long iduser) {
        return messageInterface.addMessage(messages,idpub , iduser);
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

    @GetMapping("/retrievebyitem/{idpub}")
    public List<Messages> retrievebyitem(@PathVariable("idpub") Long idpub) {
        return messageInterface.retrievebyitem(idpub);

    }

    @GetMapping("/retrievebymessagebyuser/{id}")
    public List<Messages> retrievebymessagebyuser(@PathVariable("id") Long id) {
        return messageInterface.retrievebymessagebyuser(id);
    }
}
