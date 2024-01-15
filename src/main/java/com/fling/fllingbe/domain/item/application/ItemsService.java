package com.fling.fllingbe.domain.item.application;


import com.fling.fllingbe.domain.item.domain.*;
import com.fling.fllingbe.domain.item.dto.CardItemInfo;
import com.fling.fllingbe.domain.item.dto.DecoItemInfo;
import com.fling.fllingbe.domain.item.dto.FlowerItemInfo;
import com.fling.fllingbe.domain.item.dto.GetItemsResponse;
import com.fling.fllingbe.domain.item.repository.*;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final FlowerTypeRepository flowerTypeRepository;
    private final FlowerItemRepository flowerItemRepository;
    private final DecoTypeRepository decoTypeRepository;
    private final CardItemRepository cardItemRepository;
    private final UserRepository userRepository;
    private final DecoItemRepository decoItemRepository;
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
}
