package com.fling.fllingbe.domain.item.application;

import com.fling.fllingbe.domain.item.domain.DecoItem;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.dto.DecoItemResponse;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.repository.DecoItemRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DecoItemService {
    private final DecoItemRepository decoItemRepository;
    private final UserRepository userRepository;

    public List<DecoItemResponse> getDecoItem(UUID userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new UserNotFoundException());
        List<DecoItem> decoItems = decoItemRepository.findByUser(user);

        return  decoItems.stream()
                .map(DecoItemResponse::fromEntity)
                .toList();
    }
}
