package com.fling.fllingbe.domain.item.presentation;

import com.fling.fllingbe.domain.item.application.FlowerItemService;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FlowerItemController {
    private final FlowerItemService flowerItemService;
    @GetMapping(value = "/flower-item")
    public ResponseEntity<List<FlowerItemResponse>> getFlowerItem(Authentication authentication) {
        List<FlowerItemResponse> flowerItems = flowerItemService.getFlowerItem(authentication.getName());
        return ResponseEntity.ok().body(flowerItems);
    }
}
