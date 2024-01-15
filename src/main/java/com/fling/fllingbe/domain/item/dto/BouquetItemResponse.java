package com.fling.fllingbe.domain.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BouquetItemResponse {
    List<BouquetItemInfo> wrapper;
    List<BouquetItemInfo> ribbon;
}
