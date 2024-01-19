package com.fling.fllingbe.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponse {
    private List<DecoItemDTO> decoItems;
    private List<FlowerItemDTO> flowerItems;
    private List<LetterItemDTO> letterItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DecoItemDTO {
        private Long itemId;
        private String itemName;
        private Long price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FlowerItemDTO {
        private Long itemId;
        private String flowerName;
        private Long price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LetterItemDTO {
        private Long itemId;
        private String letterName;
        private Long price;
    }
}