package com.fling.fllingbe.domain.coin.domain;

import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.global.shared.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long coinId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Integer coin;

    @Builder.Default
    @Column
    private Boolean hasPickCoinToday = false;

}
