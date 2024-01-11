package com.fling.fllingbe.domain.bouquet.application;


import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.bouquet.repository.BouquetRepository;
import com.fling.fllingbe.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BouquetService {
    private final  BouquetRepository bouquetRepository;

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
}
