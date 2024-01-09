package com.fling.fllingbe.domain.flower.application;


import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlowerService {
    public String writeLetter(FlowerRequest request, UUID id) {

        return "test";
    }
}
