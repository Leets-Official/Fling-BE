package com.fling.fllingbe.domain.flower.presentation;


import com.fling.fllingbe.domain.flower.application.FlowerService;
import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}
