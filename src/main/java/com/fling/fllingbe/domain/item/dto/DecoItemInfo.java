package com.fling.fllingbe.domain.item.dto;


import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.DecoType;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DecoItemInfo {
    private Long id;
    private String type;
    private boolean owned;
    public static DecoItemInfo fromEntity(DecoItem decoItem) {
        DecoItemInfo decoItemInfo = DecoItemInfo.builder()
                .id(decoItem.getDecoItemId())
                .type(decoItem.getDecoType().getDecoTypeName())
                .owned(decoItem.isOwned())
                .build();
        return decoItemInfo;
    }
}
