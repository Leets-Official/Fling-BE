package com.fling.fllingbe.domain.flower.application;


import com.fling.fllingbe.domain.flower.domain.Flower;
import com.fling.fllingbe.domain.flower.domain.FlowerRepository;
import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlowerService {
    final private FlowerRepository flowerRepository;
    public String writeLetter(FlowerRequest request, UUID id) {
        Flower flower = new Flower().builder()
                .letter(request.getLetter())
                .build();
        return "test";
    }
}
