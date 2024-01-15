package com.fling.fllingbe.domain.item.application;


import com.fling.fllingbe.domain.item.domain.*;
import com.fling.fllingbe.domain.item.dto.*;
import com.fling.fllingbe.domain.item.repository.*;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final FlowerItemRepository flowerItemRepository;
    private final DecoTypeRepository decoTypeRepository;
    private final CardItemRepository cardItemRepository;
    private final UserRepository userRepository;
    private final DecoItemRepository decoItemRepository;
    private final RibbonRepository ribbonRepository;
    private final WrapperTypeRepository wrapperTypeRepository;
    public GetItemsResponse getItems(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UserNotFoundException());
        List<FlowerItem> flowerItemList = flowerItemRepository.findAllByUser(user);
        List<FlowerItemInfo> flowerItemInfoList = flowerItemList.stream().map(FlowerItemInfo::fromEntity).toList();

        DecoType ignoreDecoType = decoTypeRepository.findByDecoTypeName("undefined").get();
        List<DecoItem> decoItemList = decoItemRepository.findAllByUserAndDecoTypeIsNot(user,ignoreDecoType);
        List<DecoItemInfo> decoItemInfoList = decoItemList.stream().map(DecoItemInfo::fromEntity).toList();

        List<CardItem> cardItemList = cardItemRepository.findByUser(user);
        List<CardItemInfo> cardItemInfoList = cardItemList.stream().map(CardItemInfo::fromEntity).toList();
        return new GetItemsResponse(flowerItemInfoList,decoItemInfoList,cardItemInfoList);
    }
    public BouquetItemResponse getBouquetItemResponse() {
        List<BouquetItemInfo> ribbonInfoList = new ArrayList<>();
        List<RibbonType> ribbonTypeList = ribbonRepository.findAll();
        for (RibbonType ribbonType : ribbonTypeList) {
            BouquetItemInfo bouquetItemInfo = BouquetItemInfo.builder()
                    .id(ribbonType.getRibbonTypeId())
                    .type(ribbonType.getRibbonName())
                    .build();
            ribbonInfoList.add(bouquetItemInfo);
        }

        List<BouquetItemInfo> wrapperInfoList = new ArrayList<>();
        List<WrapperType> wrapperTypeList = wrapperTypeRepository.findAll();
        for (WrapperType wrapperType : wrapperTypeList) {
            BouquetItemInfo bouquetItemInfo = BouquetItemInfo.builder()
                    .id(wrapperType.getWrapperTypeId())
                    .type(wrapperType.getWrapperName())
                    .build();
            wrapperInfoList.add(bouquetItemInfo);
        }
        BouquetItemResponse bouquetItemResponse = BouquetItemResponse.builder()
                .ribbon(ribbonInfoList)
                .wrapper(wrapperInfoList)
                .build();
        return bouquetItemResponse;
    }
}
