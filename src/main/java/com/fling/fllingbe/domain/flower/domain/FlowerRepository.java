package com.fling.fllingbe.domain.flower.domain;

import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlowerRepository extends JpaRepository<Flower,Long> {
    Optional<Flower> findById(Long flowerId);
    Optional<Flower> findByReceiver(User receiver);
}
