package com.fling.fllingbe.domain.item.dto;

import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DecoItemResponse {
    private String decoType;
    private Boolean isUsing;
    private Long Position;

    public static DecoItemResponse fromEntity(DecoItem decoItem) {
        return new DecoItemResponse(decoItem.getDecoType().getDecoTypeName(), decoItem.getIsUsing(),decoItem.getDecoType().getPosition());
    }
}
