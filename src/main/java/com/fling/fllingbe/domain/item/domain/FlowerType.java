package com.fling.fllingbe.domain.item.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FlowerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flower_type_id")
    private Long flowerTypeId;

    @Column(name = "flower_name")
    private String flowerName;

    @Column
    private String description;

    @Column(name = "price")
    private Long price;
}
