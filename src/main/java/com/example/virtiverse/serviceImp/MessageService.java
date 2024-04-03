package com.example.virtiverse.serviceImp;

import com.example.virtiverse.entities.Messages;
import com.example.virtiverse.repository.MessageRepository;
import com.example.virtiverse.serviceInterface.MessageInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService implements MessageInterface {
    MessageRepository messageRepository;
    @Override
    public List<Messages> retrieveAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Messages addMessage(Messages messages) {
        return messageRepository.save(messages);
    }

    @Override
    public void removemessage(Long idmessage) {
        messageRepository.deleteById(idmessage);

    }

    @Override
    public Messages retrievemessage(Long idmessage) {
        return messageRepository.findById(idmessage).orElse(null);
    }

    @Override
    public Messages updatemessage(Messages messages) {
        return messageRepository.save(messages);
    }
}
