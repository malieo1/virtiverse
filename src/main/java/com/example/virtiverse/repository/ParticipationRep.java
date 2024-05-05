package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Event;
import com.example.virtiverse.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ParticipationRep extends JpaRepository<Participation,Long> {
     default boolean isValidTunisianPhoneNumber(String numTel) {
        return numTel.matches("^(00216|\\+216)?[0-9]{8}$");
    }
    default boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    List<Participation> findByUserId(Long id);
    @Query("SELECT p.user.id FROM Participation p WHERE p.idParticipation = :idParticipation")
    String findIdByParticipationId(@Param("idParticipation") Long idParticipation);

    @Query("SELECT p.event.idEvent, COUNT(p.idParticipation) FROM Participation p GROUP BY p.event.idEvent")
    List<Object[]> countParticipationsByEvent();


}
