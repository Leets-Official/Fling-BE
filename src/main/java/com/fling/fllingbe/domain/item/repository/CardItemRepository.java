package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.CardType;
import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardItemRepository extends JpaRepository<CardItem,Long> {
    Optional<CardItem> findById(Long CardItemId);
    List<CardItem> findByUser(User user);
    Optional<CardItem> findByUserAndCardType(User user, CardType cardType);
}
