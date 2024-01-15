package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.DecoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DecoTypeRepository extends JpaRepository<DecoType, Long> {
    Optional<DecoType> findById(Long decoTypeId);
    Optional<DecoType> findByDecoTypeName(String decoTypeName);
    List<DecoType> findAllByDecoTypeIdIsNot(Long decoTypeId);
}
