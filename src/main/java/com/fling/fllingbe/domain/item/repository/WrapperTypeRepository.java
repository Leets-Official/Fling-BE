package com.fling.fllingbe.domain.item.repository;

import com.fling.fllingbe.domain.item.domain.WrapperType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WrapperTypeRepository extends JpaRepository<WrapperType,Long> {
    Optional<WrapperType> findByWrapperName(String wrapperName);
}
