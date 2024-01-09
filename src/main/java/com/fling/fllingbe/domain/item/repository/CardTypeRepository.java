package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardTypeRepository extends JpaRepository<CardType,Long> {
    Optional<CardType> findById(Long cardTypeId);
}
