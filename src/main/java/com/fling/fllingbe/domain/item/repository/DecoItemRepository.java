package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.DecoType;
import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DecoItemRepository extends JpaRepository<DecoItem,Long> {
    Optional<DecoItem> findById(Long decoItemId);
    List<DecoItem> findByUser(User user);
    Optional<DecoItem> findByDecoType(DecoType decoType);
    Optional<DecoItem> findByUserAndDecoType(User user, DecoType decoType);
    List<DecoItem> findAllByUserAndIsUsing(User user,boolean isUsing);
}
