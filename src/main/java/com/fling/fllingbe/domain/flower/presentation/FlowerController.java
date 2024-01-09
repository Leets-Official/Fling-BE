package com.fling.fllingbe.domain.flower.presentation;


import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FlowerController {
    @PostMapping(value = "/flower")
    public ResponseEntity<String> writeLetter(FlowerRequest flowerRequest, UUID id) {
        return ResponseEntity.ok().body("test");
    }
}
