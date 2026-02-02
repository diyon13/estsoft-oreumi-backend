package com.example.step14.persistence;

import com.example.step14.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}