package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRep extends JpaRepository<Participation,Long> {
     default boolean isValidTunisianPhoneNumber(String numTel) {
        return numTel.matches("^(00216|\\+216)?[0-9]{8}$");
    }
    default boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    List<Participation> findByUserUserName(String userName);

}
