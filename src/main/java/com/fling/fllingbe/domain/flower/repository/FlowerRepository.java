package com.fling.fllingbe.domain.flower.repository;

import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.flower.domain.Flower;
import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlowerRepository extends JpaRepository<Flower,Long> {
    Optional<Flower> findById(Long flowerId);
    Optional<Flower> findByReceiver(User receiver);
    List<Flower> findAllByReceiver(User receiver);
    List<Flower> findAllByBouquetId(Bouquet bouquet);
    List<Flower> findAllBySender(User sender);
}
