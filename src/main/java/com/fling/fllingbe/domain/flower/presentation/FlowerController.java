package com.fling.fllingbe.domain.flower.presentation;


import com.fling.fllingbe.domain.flower.application.FlowerService;
import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import com.fling.fllingbe.domain.flower.dto.GetLetter;
import com.fling.fllingbe.domain.flower.dto.ReceivedFlower;
import com.fling.fllingbe.domain.flower.dto.SentFlower;
import com.fling.fllingbe.domain.item.dto.GetItemById;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FlowerController {
    final private FlowerService flowerService;
    @PostMapping(value = "/flower/{receiverId}")
    public ResponseEntity<String> writeLetter(@RequestBody FlowerRequest flowerRequest, @PathVariable("receiverId") UUID receiverId, Authentication authentication) {
        String response = flowerService.writeLetter(flowerRequest, authentication.getName(), receiverId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/letter-sent")
    public ResponseEntity<List<SentFlower>> getSendedFlower(Authentication authentication) {
        List<SentFlower> sentFlowerList = flowerService.getSendedFlower(authentication.getName());
        return ResponseEntity.ok().body(sentFlowerList);
    }

    @GetMapping(value = "/letter-received")
    public ResponseEntity<List<ReceivedFlower>> getReceivedFlower(Authentication authentication) {
        List<ReceivedFlower> receivedFlowerList = flowerService.getReceivedFlower(authentication.getName());
        return ResponseEntity.ok().body(receivedFlowerList);
    }

    @GetMapping(value = "/flower")
    public ResponseEntity<GetLetter> getLetter(@RequestBody GetItemById request) {
        GetLetter getLetter = flowerService.getLetter(request);
        return ResponseEntity.ok().body(getLetter);
    }
}
