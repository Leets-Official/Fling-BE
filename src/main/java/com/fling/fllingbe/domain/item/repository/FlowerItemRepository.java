package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlowerItemRepository extends JpaRepository<FlowerItem, Long> {
    Optional<FlowerItem> findById(Long flowerItemId);
    List<FlowerItem> findAllByUser(User user);
    Optional<FlowerItem> findByUser(User user);
    Optional<FlowerItem> findByUserAndFlowerType(User user,FlowerType flowerType);
}
