package com.example.virtiverse.serviceInterface;

import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.entities.Messages;

import java.util.List;

public interface MessageInterface {
    List<Messages> retrieveAllMessages();
    Messages addMessage(Messages messages,Long idpub , long iduser);
    void removemessage (Long idmessage);
    Messages retrievemessage (Long idmessage);
    Messages updatemessage (Messages messages);

    List<Messages> retrievebyitem(Long idpub);

    List<Messages> retrievebymessagebyuser(Long id);
}
