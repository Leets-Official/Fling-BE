package com.fling.fllingbe.domain.item.dto;


import com.fling.fllingbe.domain.item.domain.DecoItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetItemsResponse {
    private List<FlowerItemInfo> flowers;
    private List<DecoItemInfo> decoItems;
    private List<CardItemInfo> cardItems;
}
