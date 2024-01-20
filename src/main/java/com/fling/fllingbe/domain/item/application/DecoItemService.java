package com.fling.fllingbe.domain.item.application;

import com.fling.fllingbe.domain.bouquet.dto.UpdateBouquetRequest;
import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.CardType;
import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.DecoType;
import com.fling.fllingbe.domain.item.dto.*;
import com.fling.fllingbe.domain.item.repository.DecoItemRepository;
import com.fling.fllingbe.domain.item.repository.DecoTypeRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecoItemService {
    private final DecoItemRepository decoItemRepository;
    private final UserRepository userRepository;
    private final DecoTypeRepository decoTypeRepository;
    public List<DecoItemResponse> getDecoItem(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException());
        List<DecoItem> decoItems = decoItemRepository.findByUser(user);

        return  decoItems.stream()
                .map(DecoItemResponse::fromEntity)
                .toList();
    }
    public List<DecoItem> toTrue(User user, UpdateBouquetRequest request) {
        List<DecoItem> decoItemList = new ArrayList<>();
        DecoType decoType1 = decoTypeRepository.findByDecoTypeName(request.getItem1()).get();
        DecoType decoType2 = decoTypeRepository.findByDecoTypeName(request.getItem2()).get();
        DecoType decoType3 = decoTypeRepository.findByDecoTypeName(request.getItem3()).get();
        DecoItem decoItem1 = decoItemRepository.findByUserAndDecoType(user,decoType1).get();
        DecoItem decoItem2 = decoItemRepository.findByUserAndDecoType(user,decoType2).get();
        DecoItem decoItem3 = decoItemRepository.findByUserAndDecoType(user,decoType3).get();
        DecoItem newDecoItem1 = new DecoItem().builder()
                .decoItemId(decoItem1.getDecoItemId())
                .decoType(decoType1)
                .user(user)
                .isUsing(true)
                .owned(true)
                .build();
        decoItemRepository.save(newDecoItem1);
        DecoItem newDecoItem2 = new DecoItem().builder()
                .decoItemId(decoItem2.getDecoItemId())
                .decoType(decoType2)
                .user(user)
                .isUsing(true)
                .owned(true)
                .build();
        decoItemRepository.save(newDecoItem2);
        DecoItem newDecoItem3 = new DecoItem().builder()
                .decoItemId(decoItem3.getDecoItemId())
                .decoType(decoType3)
                .user(user)
                .isUsing(true)
                .owned(true)
                .build();
        decoItemRepository.save(newDecoItem3);
        decoItemList.add(newDecoItem1);
        decoItemList.add(newDecoItem2);
        decoItemList.add(newDecoItem3);
        return decoItemList;
    }
    public void toFalse(User user,List<DecoItem> decoItemList) {
        for (DecoItem decoItem : decoItemList) {
            DecoItem newDecoItem = new DecoItem().builder()
                    .decoItemId(decoItem.getDecoItemId())
                    .decoType(decoItem.getDecoType())
                    .user(user)
                    .isUsing(false)
                    .owned(true)
                    .build();
            decoItemRepository.save(newDecoItem);
        }
    }
    public void createDefaultDecoItem(User user) {
        List<DecoType> decoTypeList = decoTypeRepository.findAll();
        for (DecoType decoType : decoTypeList) {
            DecoItem decoItem = DecoItem.builder()
                    .user(user)
                    .decoType(decoType)
                    .owned(decoType.getPrice() == 0)
                    .isUsing(false)
                    .build();
            decoItemRepository.save(decoItem);
        }
    }
    public GetItemResponse getDecoItemInfo(GetItemById request) {
        DecoType decoType = decoTypeRepository.findById(request.getId()).get();
        GetItemResponse getItemResponse = GetItemResponse.builder()
                .itemName(decoType.getDecoTypeName())
                .description(decoType.getDescription())
                .build();
        return getItemResponse;
    }

    public String addNewDecoItem(AddDecoItemRequest request) {
        DecoType decoType = DecoType.builder()
                .decoTypeName(request.getDecoType())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        decoTypeRepository.save(decoType);
        List<User> userList = userRepository.findAll();
        DecoType newDecoType = decoTypeRepository.findByDecoTypeName(request.getDecoType()).get();
        for (User user : userList) {
            DecoItem decoItem = DecoItem.builder()
                    .user(user)
                    .decoType(newDecoType)
                    .isUsing(false)
                    .owned(newDecoType.getPrice() == 0)
                    .build();
            decoItemRepository.save(decoItem);
        }
        return "아이템 추가가 완료되었습니다.";
    }
}
