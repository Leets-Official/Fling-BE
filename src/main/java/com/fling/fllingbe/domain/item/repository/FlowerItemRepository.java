package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.FlowerItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlowerItemRepository extends JpaRepository<FlowerItem, Long> {
    Optional<FlowerItem> findById(Long flowerItemId);
    Optional<FlowerItem> findByUserId(Long userId);
}
