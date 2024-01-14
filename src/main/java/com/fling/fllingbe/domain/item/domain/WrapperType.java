package com.fling.fllingbe.domain.item.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WrapperType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long wrapperTypeId;

    @Column
    private String wrapperName;
}
