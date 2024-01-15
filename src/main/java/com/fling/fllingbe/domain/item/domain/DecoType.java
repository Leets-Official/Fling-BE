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
public class DecoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long decoTypeId;

    @Column
    private String decoTypeName;

    @Column
    private String description;

    @Column
    private Long price;
}
