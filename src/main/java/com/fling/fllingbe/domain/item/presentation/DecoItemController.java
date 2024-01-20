package com.fling.fllingbe.domain.item.presentation;

import com.fling.fllingbe.domain.item.application.DecoItemService;
import com.fling.fllingbe.domain.item.dto.*;
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
public class DecoItemController {
    final private DecoItemService decoItemService;

    @GetMapping(value = "/decoitem")
    public ResponseEntity<List<DecoItemResponse>> getDecoItem(Authentication authentication) {
        List<DecoItemResponse> decoItemResponses = decoItemService.getDecoItem(authentication.getName());
        return ResponseEntity.ok().body(decoItemResponses);
    }

    @GetMapping(value = "/decoitem-info")
    public ResponseEntity<GetItemResponse> getDecoItemInfo(@RequestBody GetItemById request) {
        GetItemResponse getItemResponse = decoItemService.getDecoItemInfo(request);
        return ResponseEntity.ok().body(getItemResponse);
    }

    @PostMapping("/adddeco")
    public ResponseEntity<String> addNewDecoItem(@RequestBody AddDecoItemRequest request) {
        String response = decoItemService.addNewDecoItem(request);
        return ResponseEntity.ok().body(response);
    }
}
