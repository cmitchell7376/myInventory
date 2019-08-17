package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
}