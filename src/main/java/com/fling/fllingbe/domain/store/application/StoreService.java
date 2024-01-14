package com.fling.fllingbe.domain.store.application;

import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.repository.FlowerItemRepository;
import com.fling.fllingbe.domain.item.repository.FlowerTypeRepository;
import com.fling.fllingbe.domain.store.dto.FlowerPurchaseRequest;
import com.fling.fllingbe.domain.store.exception.FlowerNotFoundException;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final FlowerItemRepository flowerItemRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final UserRepository userRepository;

    public void purchaseFlower(FlowerPurchaseRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        FlowerItem flowerItem = flowerItemRepository.findByUserAndFlowerType(user,
                        flowerTypeRepository.findById(request.getFlowerItemId()).orElseThrow(()-> new FlowerNotFoundException()))
                .orElseThrow(()-> new IllegalArgumentException("Flower item not found for user"));

        flowerItem.setCount(flowerItem.getCount() + request.getCount());
        flowerItemRepository.save(flowerItem);
    }
}
