package com.example.virtiverse.repository;

import com.example.virtiverse.entities.LostandFound;
import com.example.virtiverse.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages, Long> {
  List<Messages> findByIdpubIdPubOrderBySendat(long idpub);


  List<Messages> findByIduserId(long id);

}
