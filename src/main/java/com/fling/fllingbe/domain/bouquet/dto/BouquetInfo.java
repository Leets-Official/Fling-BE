package com.fling.fllingbe.domain.bouquet.dto;


import com.fling.fllingbe.domain.flower.dto.FlowerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BouquetInfo {
    private Long bouquetId;
    private List<FlowerInfo> flowers;
}
