package com.fling.fllingbe.domain.item.presentation;


import com.fling.fllingbe.domain.item.application.ItemsService;
import com.fling.fllingbe.domain.item.dto.GetItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemsController {
    private final ItemsService itemsService;

    @GetMapping("/items")
    public ResponseEntity<GetItemsResponse> getItems() {
        GetItemsResponse getItemsResponse = itemsService.getItems();
        return ResponseEntity.ok().body(getItemsResponse);
    }
}
