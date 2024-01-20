package com.fling.fllingbe.domain.item.presentation;

import com.fling.fllingbe.domain.item.application.FlowerItemService;
import com.fling.fllingbe.domain.item.dto.AddFlowerItemRequest;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.dto.GetItemById;
import com.fling.fllingbe.domain.item.dto.GetItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlowerItemController {
    private final FlowerItemService flowerItemService;

    @GetMapping(value = "/floweritem")
    public ResponseEntity<List<FlowerItemResponse>> getFlowerItem(Authentication authentication) {
        List<FlowerItemResponse> flowerItems = flowerItemService.getFlowerItem(authentication.getName());
        return ResponseEntity.ok().body(flowerItems);
    }

    @GetMapping("/floweritem-info")
    public ResponseEntity<GetItemResponse> getFlowerItemInfo(@RequestBody GetItemById request) {
        GetItemResponse getItemResponse = flowerItemService.getFlowerItemInfo(request);
        return ResponseEntity.ok().body(getItemResponse);
    }

    @PostMapping("/addflower")
    public ResponseEntity<String> addNewFlower(@RequestBody AddFlowerItemRequest request) {
        String response = flowerItemService.addNewFlowerItem(request);
        return ResponseEntity.ok().body(response);
    }
}
