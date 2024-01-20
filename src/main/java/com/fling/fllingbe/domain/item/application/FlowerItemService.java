package com.fling.fllingbe.domain.item.application;

import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import com.fling.fllingbe.domain.item.dto.AddFlowerItemRequest;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.dto.GetItemById;
import com.fling.fllingbe.domain.item.dto.GetItemResponse;
import com.fling.fllingbe.domain.item.repository.FlowerItemRepository;
import com.fling.fllingbe.domain.item.repository.FlowerTypeRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlowerItemService {
    private final FlowerItemRepository flowerItemRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final UserRepository userRepository;
    public List<FlowerItemResponse> getFlowerItem(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException());
        List<FlowerItem> flowerItems = flowerItemRepository.findAllByUser(user);

        return  flowerItems.stream()
                .map(FlowerItemResponse::fromEntity)
                .collect(Collectors.toList());
    }
    public FlowerType minusFlowerItem(FlowerRequest request, User user) {
        FlowerType flowerType = flowerTypeRepository.findByFlowerName(request.getFlowerType()).get();
        FlowerItem flowerItem = flowerItemRepository.findByUserAndFlowerType(user, flowerType).get();
        if (flowerType.getPrice() != 0) {
            FlowerItem newFlowerItem = new FlowerItem().builder()
                    .flowerItemId(flowerItem.getFlowerItemId())
                    .user(user)
                    .flowerType(flowerType)
                    .owned(true)
                    .count(flowerItem.getCount() - 1)
                    .build();
            flowerItemRepository.save(newFlowerItem);
        } else {
            FlowerItem newFlowerItem = new FlowerItem().builder()
                    .flowerItemId(flowerItem.getFlowerItemId())
                    .user(user)
                    .flowerType(flowerType)
                    .owned(true)
                    .count(flowerItem.getCount())
                    .build();
            flowerItemRepository.save(newFlowerItem);
        }
        return flowerType;
    }
    public void createDefaultFlowerItem(User user) {
        List<FlowerType> flowerItemList = flowerTypeRepository.findAll();
        for (FlowerType flowerType : flowerItemList) {
            Long count = (flowerType.getPrice() == 0) ? -1L : 0L;
            FlowerItem flowerItem = FlowerItem.builder()
                    .user(user)
                    .count(count)
                    .flowerType(flowerType)
                    .owned(flowerType.getPrice() == 0)
                    .build();
            flowerItemRepository.save(flowerItem);

        }
    }

    public GetItemResponse getFlowerItemInfo(GetItemById request) {
        FlowerType flowerType = flowerTypeRepository.findById(request.getId()).get();
        GetItemResponse getItemResponse = GetItemResponse.builder()
                .itemName(flowerType.getFlowerName())
                .description(flowerType.getDescription())
                .build();
        return getItemResponse;
    }

    public String addNewFlowerItem(AddFlowerItemRequest request) {
        FlowerType flowerType = FlowerType.builder()
                .flowerName(request.getFlowerType())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        flowerTypeRepository.save(flowerType);
        List<User> userList = userRepository.findAll();
        FlowerType newFlowerType = flowerTypeRepository.findByFlowerName(request.getFlowerType()).get();
        Long count = (newFlowerType.getPrice() == 0)? -1L : 0L;
        for (User user : userList) {
            FlowerItem flowerItem = FlowerItem.builder()
                    .user(user)
                    .flowerType(newFlowerType)
                    .count(count)
                    .owned(newFlowerType.getPrice() == 0)
                    .build();
            flowerItemRepository.save(flowerItem);
        }
        return "아이템 추가가 완료되었습니다.";
    }
}
