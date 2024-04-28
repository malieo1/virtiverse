package com.example.virtiverse.repository;

import com.example.virtiverse.entities.Maison;
import com.example.virtiverse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUserName(String userName);
}
