package com.fling.fllingbe.domain.item.domain;


import com.fling.fllingbe.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"FlowerItem\"")
public class FlowerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flower_item_id")
    private Long flowerItemId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "flower_type_id")
    private FlowerType flowerType;

    @Column
    private Long count;
}
