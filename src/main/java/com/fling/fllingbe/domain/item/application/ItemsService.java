package com.fling.fllingbe.domain.item.application;


import com.fling.fllingbe.domain.item.domain.*;
import com.fling.fllingbe.domain.item.dto.CardItemInfo;
import com.fling.fllingbe.domain.item.dto.DecoItemInfo;
import com.fling.fllingbe.domain.item.dto.FlowerItemInfo;
import com.fling.fllingbe.domain.item.dto.GetItemsResponse;
import com.fling.fllingbe.domain.item.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final FlowerTypeRepository flowerTypeRepository;
    private final DecoTypeRepository decoTypeRepository;
    private final CardTypeRepository cardTypeRepository;
    public GetItemsResponse getItems() {
        List<FlowerType> flowerTypeList = flowerTypeRepository.findAll();
        List<FlowerItemInfo> flowerItemInfoList = flowerTypeList.stream().map(FlowerItemInfo::fromEntity).toList();
        List<DecoType> decoTypeList = decoTypeRepository.findAllByDecoTypeIdIsNot(1L);
        List<DecoItemInfo> decoItemInfoList = decoTypeList.stream().map(DecoItemInfo::fromEntity).toList();
        List<CardType> cardTypeList = cardTypeRepository.findAll();
        List<CardItemInfo> cardItemInfoList = cardTypeList.stream().map(CardItemInfo::fromEntity).toList();
        return new GetItemsResponse(flowerItemInfoList,decoItemInfoList,cardItemInfoList);
    }
}
