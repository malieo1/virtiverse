package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Cart;
import com.example.virtiverse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
