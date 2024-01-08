package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.FlowerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlowerTypeRepository extends JpaRepository<FlowerType, Long> {
    Optional<FlowerType> findById(Long flowerTypeId);
}
