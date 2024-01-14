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

    public static DecoItemInfo fromEntity(DecoType decoType) {
        DecoItemInfo decoItemInfo = DecoItemInfo.builder()
                .id(decoType.getDecoTypeId())
                .type(decoType.getDecoTypeName())
                .build();
        return decoItemInfo;
    }
}
