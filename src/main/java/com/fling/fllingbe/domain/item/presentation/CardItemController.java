package com.fling.fllingbe.domain.item.presentation;


import com.fling.fllingbe.domain.item.application.CardItemService;
import com.fling.fllingbe.domain.item.dto.CardItemResponse;
import com.fling.fllingbe.domain.item.dto.GetItemById;
import com.fling.fllingbe.domain.item.dto.GetItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardItemController {
    final private CardItemService cardItemService;

    @GetMapping(value = "/carditem")
    public ResponseEntity<List<CardItemResponse>> getCardItem(Authentication authentication) {
        List<CardItemResponse> cardItems = cardItemService.getCardItem(authentication.getName());
        return ResponseEntity.ok().body(cardItems);
    }

    @GetMapping(value = "/carditem-info")
    public ResponseEntity<GetItemResponse> getCardItemInfo(@RequestBody GetItemById getItemById) {
        GetItemResponse getItemResponse = cardItemService.getCardItemInfo(getItemById);
        return ResponseEntity.ok().body(getItemResponse);
    }
}
