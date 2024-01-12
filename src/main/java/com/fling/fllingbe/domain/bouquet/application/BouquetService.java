package com.fling.fllingbe.domain.bouquet.application;


import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.bouquet.dto.CreateBouquetRequest;
import com.fling.fllingbe.domain.bouquet.repository.BouquetRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BouquetService {
    private final  BouquetRepository bouquetRepository;
    private final UserRepository userRepository;
    public Bouquet createNewBouquet(User user) {
        Bouquet receiverBouquet = bouquetRepository.findByUser(user).get();
        Bouquet newBouquet = new Bouquet().builder()
                .user(user)
                .ribbon(receiverBouquet.getRibbon())
                .wrapper(receiverBouquet.getWrapper())
                .build();
        bouquetRepository.save(newBouquet);
        return newBouquet;
    }

    public String createFirstBouquet(UUID id , CreateBouquetRequest request) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException());
        Bouquet newBouquet = new Bouquet().builder()
                .user(user)
                .ribbon(request.getRibbon())
                .wrapper(request.getWrapper())
                .build();
        bouquetRepository.save(newBouquet);
        return "꽃다발 생성에 성공하였습니다.";
    }
}
