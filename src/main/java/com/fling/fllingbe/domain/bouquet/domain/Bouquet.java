package com.fling.fllingbe.domain.bouquet.domain;

import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.DecoType;
import com.fling.fllingbe.domain.item.domain.RibbonType;
import com.fling.fllingbe.domain.item.domain.WrapperType;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.global.shared.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Wrapper;
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

    @ManyToOne
    @JoinColumn
    private DecoType decoItem1;

    @ManyToOne
    @JoinColumn
    private DecoType decoItem2;

    @ManyToOne
    @JoinColumn
    private DecoType decoItem3;

    @ManyToOne
    @JoinColumn
    private RibbonType ribbonType;

    @ManyToOne
    @JoinColumn
    private WrapperType wrapperType;
}
