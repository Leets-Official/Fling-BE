package com.fling.fllingbe.domain.flower.presentation;


import com.fling.fllingbe.domain.flower.application.FlowerService;
import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FlowerController {
    final private FlowerService flowerService;
    @PostMapping(value = "/flower/{receiverId}/{senderId}")
    public ResponseEntity<String> writeLetter(@RequestBody FlowerRequest flowerRequest, @PathVariable("receiverId") UUID receiverId, @PathVariable("senderId") UUID senderId) {
        String response = flowerService.writeLetter(flowerRequest, senderId, receiverId);
        return ResponseEntity.ok().body(response);
    }
}
