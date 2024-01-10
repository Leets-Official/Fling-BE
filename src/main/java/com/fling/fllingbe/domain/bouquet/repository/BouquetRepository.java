package com.fling.fllingbe.domain.bouquet.repository;

import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BouquetRepository extends JpaRepository<Bouquet,Long> {
    Optional<Bouquet> findById (Long bouquetId);
    Optional<Bouquet> findByUser(User user);
    List<Bouquet> findAllByUser(User user);
}
