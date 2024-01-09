package com.fling.fllingbe.domain.bouquet.domain;

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
public class Bouquet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long bouquetId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String ribbon;

    @Column
    private String wrapper;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt = LocalDateTime.now();
}
