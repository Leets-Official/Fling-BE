package com.fling.fllingbe.domain.item.application;

import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.repository.FlowerItemRepository;
import com.fling.fllingbe.domain.item.repository.FlowerTypeRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlowerItemService {
    private final FlowerItemRepository flowerItemRepository;
    private final UserRepository userRepository;
    public List<FlowerItemResponse> getFlowerItem(UUID userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new UserNotFoundException());
        List<FlowerItem> flowerItems = flowerItemRepository.findAllByUser(user);

        return  flowerItems.stream()
                .map(FlowerItemResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
