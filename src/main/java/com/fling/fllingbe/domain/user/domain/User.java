package com.fling.fllingbe.domain.user.domain;

import com.fling.fllingbe.global.shared.domain.BaseTimeEntity;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"User\"")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID userId;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String nickname;

    @Column
    private LocalDateTime dDay;

    @Column
    private String description;

    @Column
    private LocalDateTime deletedAt;
    @PreDestroy
    public void preDestroy() {
        this.deletedAt = LocalDateTime.now();
    }
}
