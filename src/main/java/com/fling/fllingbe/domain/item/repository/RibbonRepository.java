package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.RibbonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RibbonRepository extends JpaRepository<RibbonType, Long> {
    Optional<RibbonType> findByRibbonName(String ribbonName);
}
