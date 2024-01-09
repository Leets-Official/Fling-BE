package com.fling.fllingbe.domain.flower.domain;

import com.fling.fllingbe.domain.item.domain.CardType;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.global.shared.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flower extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long flowerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User receiver;

    @OneToOne
    @JoinColumn(name = "card_type_id")
    private CardType cardType;

    @OneToOne
    @JoinColumn(name = "flower_type_id")
    private FlowerType flowerType;

    @Column
    private String letter;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt = LocalDateTime.now();
}