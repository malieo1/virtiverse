package com.example.virtiverse.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SMSRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_SMS;
    String recipient;
    String message;
}
