package com.fling.fllingbe.domain.user.repository;

import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByUserId(UUID userId);
}
